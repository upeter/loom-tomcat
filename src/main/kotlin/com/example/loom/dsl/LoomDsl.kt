package com.example.loom.dsl

import jdk.incubator.concurrent.StructuredTaskScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun <T> virtualScope(propagate:Boolean = true, block:StructuredTaskScope.ShutdownOnFailure.() -> T) {
    StructuredTaskScope.ShutdownOnFailure().use{scope ->
        scope.block()
        scope.join()
        if(propagate) scope.throwIfFailed()
    }
}

fun <T> StructuredTaskScope.ShutdownOnFailure.async(block: () -> T):Future<T> =
    fork(block)


fun <T> Future<T>.await():T  = this.get()

fun <T> List<Future<T>>.awaitAll():List<T>  = this.map { it.await() }


fun <T> ExecutorService.async(block: () -> T):Future<T> {
    return this.submit(block)
}

