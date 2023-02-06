package org.up.utils

import jdk.incubator.concurrent.StructuredTaskScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

//fun <T> virtualScope(propagate:Boolean = true, block: StructuredTaskScope.ShutdownOnFailure.() -> T):T =
//    StructuredTaskScope.ShutdownOnFailure().use { scope -> scope.block().also { scope.join() }
//        val result = scope.block()
//        scope.join() // Join both forks
//        if(propagate)scope.throwIfFailed() // ... and propagate errors
//        result
//    }
//
//fun <T> StructuredTaskScope.ShutdownOnFailure.async(block: () -> T):Future<T> =
//    fork(block)
//
//
//fun <T> Future<T>.await():T  = this.get()
//
//fun <T> List<Future<T>>.awaitAll():List<T>  = this.map { it.await() }
//
//
//fun <T> ExecutorService.async(block: () -> T):Future<T> {
//    return this.submit(block)
//}
//
