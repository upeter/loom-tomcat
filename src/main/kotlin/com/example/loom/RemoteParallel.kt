package com.example.loom

import com.example.loom.domain.Avatar
import com.example.loom.domain.Info
import com.example.loom.dsl.async
import com.example.loom.dsl.await
import com.example.loom.dsl.virtualScope
//import com.example.loom.dsl.async
//import com.example.loom.dsl.await
//import com.example.loom.dsl.virtualScope
import com.example.utils.*
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.http.impl.client.CloseableHttpClient
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.getForEntity

@WebServlet("/parallel")
class RemoteParallel : HttpServlet() {
        override fun doGet(request: HttpServletRequest, response: HttpServletResponse) = virtualScope {
            val delay = request.getParameter("delay").toLong()
            val reply1 = async{ restTemplate.getForEntity<Avatar>("/avatar?delay=$delay").body() }
            val reply2 = async { restTemplate.getForEntity<Avatar>("/avatar?delay=$delay").body() }
            with(response) {
                contentType = "application/json"
                mapper.writeValue(writer, Info(delay, reply1.await(), reply2.await()))
            }
        }

}