package org.up.loom.dsl

import jdk.incubator.concurrent.StructuredTaskScope.ShutdownOnFailure
import java.util.concurrent.*

fun <T> virtualScope(propagate: Boolean = true, block: ShutdownOnFailure.() -> T): T =
    ShutdownOnFailure().use { scope ->
        scope.block().also {
            scope.join() // Join both forks
            if (propagate) scope.throwIfFailed() //
        }
    }

fun <T> ShutdownOnFailure.async(block:() -> T):Future<T> = this.fork(block)

fun <T> Future<T>.await() = this.get()