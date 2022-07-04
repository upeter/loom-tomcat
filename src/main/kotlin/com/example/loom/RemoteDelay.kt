package com.example.loom

import com.example.loom.domain.Avatar
import com.example.loom.domain.Info
import com.example.utils.*
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.http.impl.client.CloseableHttpClient

@WebServlet("/remote")
class RemoteDelay : HttpServlet() {
    private val httpClient: CloseableHttpClient = createClient()

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val delay = request.getParameter("delay").toLong()
        val reply = httpClient.execute(HttpGetAvatar(delay)).bodyAs<Avatar>()
        with(response) {
            contentType = "application/json"
            mapper.writeValue(writer, Info(delay, reply))
        }
    }

}