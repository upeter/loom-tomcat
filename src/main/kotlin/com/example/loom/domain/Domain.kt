package com.example.loom.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Info @JvmOverloads constructor(
    val delay:Long,
    val reply:List<Avatar> = emptyList(),
    val thread:String =  Thread.currentThread().name,
) {
    companion object {
        operator fun invoke(delay: Long, vararg reply:Avatar, thread: String = Thread.currentThread().name,) = Info(delay, reply.toList(), thread)
    }
}

data class Avatar @JsonCreator constructor(@JsonProperty("url") val url: String)