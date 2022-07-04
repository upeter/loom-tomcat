package com.example.loom.dsl

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun <T> virtualScope(block:ExecutorService.() -> T) {
    Executors.newVirtualThreadPerTaskExecutor().use(block)
}

fun <T> ExecutorService.async(block: () -> T):Future<T> {
    return this.submit(block)
}

fun <T> Future<T>.await():T  = this.get()

fun <T> List<Future<T>>.awaitAll():List<T>  = this.map { it.await() }
