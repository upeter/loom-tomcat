
@file:JvmName("HttpClient")
package com.example.utils

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.HttpHost
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.routing.HttpRoute
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager

private fun createPoolManager(): PoolingHttpClientConnectionManager = PoolingHttpClientConnectionManager().apply {
    maxTotal = 300
    defaultMaxPerRoute = 300
    setMaxPerRoute(HttpRoute(HttpHost("localhost", 8081)), 300)

}
fun createClient(): CloseableHttpClient {
    return HttpClients.custom().setConnectionManager(createPoolManager()).build()
}

val mapper = jacksonObjectMapper()

inline fun <reified T> CloseableHttpResponse.bodyAs() = mapper.readValue<T>(this.entity.content)

fun HttpGetAvatar(delay:Long) = HttpGet("http://localhost:8081/avatar?delay=$delay")

