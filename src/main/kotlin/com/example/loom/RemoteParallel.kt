package com.example.loom

import com.example.loom.domain.Avatar
import com.example.loom.domain.Info
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
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) = TODO("")

}