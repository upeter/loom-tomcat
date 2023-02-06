package org.up.loom

import com.example.loom.domain.Info
import com.example.utils.mapper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/sleep")
class ThreadInfo : HttpServlet() {

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/plain"
        val delay = request.getParameter("delay").toLong()
        Thread.sleep(delay)
        with(response) {
            contentType = "application/json"
            mapper.writeValue(writer, Info(delay))
        }
    }
}