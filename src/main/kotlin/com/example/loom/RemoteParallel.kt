package com.example.loom

import com.example.loom.domain.Avatar
import com.example.loom.domain.Info
import com.example.loom.dsl.async
import com.example.loom.dsl.await
import com.example.loom.dsl.virtualScope
import com.example.utils.HttpGetAvatar
import com.example.utils.bodyAs
import com.example.utils.createClient
import com.example.utils.mapper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.http.impl.client.CloseableHttpClient

@WebServlet("/parallel")
class RemoteParallel : HttpServlet() {
    private val httpClient: CloseableHttpClient = createClient()

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) = virtualScope {
        val delay = request.getParameter("delay").toLong()
        val reply1 = async { httpClient.execute(HttpGetAvatar(delay)).bodyAs<Avatar>() }
        val reply2 = async { httpClient.execute(HttpGetAvatar(delay)).bodyAs<Avatar>() }
        with(response) {
            contentType = "application/json"
            mapper.writeValue(writer, Info(delay, reply1.await(),reply2.await()))
        }
    }

}