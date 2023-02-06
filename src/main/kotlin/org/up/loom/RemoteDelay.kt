package org.up.loom

import com.example.loom.domain.Avatar
import com.example.loom.domain.Info
import com.example.utils.*
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.client.getForEntity

@WebServlet("/remote")
class RemoteDelay : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val delay = request.getParameter("delay").toLong()
        val reply = restTemplate.getForEntity<Avatar>("/avatar?delay=$delay").body()
        with(response) {
            contentType = "application/json"
            mapper.writeValue(writer, Info(delay, reply))
        }
    }

}