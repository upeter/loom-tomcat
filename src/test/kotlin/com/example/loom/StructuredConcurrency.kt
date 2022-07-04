package com.example.loom

import jdk.incubator.concurrent.StructuredTaskScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class StructuredConcurrency {


    @Test
    fun `structured concurrency with Coroutines`():Unit = runBlocking{
            coroutineScope {
                launch { println("First: A") }
                launch { println("First: B") }
            }
            coroutineScope {
                launch { println("Second: A") }
                launch { println("Second: B") }
            }
    }

    @Test
    fun `structured concurrency with VirtualThreads`():Unit {

        StructuredTaskScope.ShutdownOnFailure().use{ scope ->
            scope.fork { println("A: First") }
            scope.fork { println("A: Second") }
            scope.join()
            scope.throwIfFailed()
        }
        StructuredTaskScope.ShutdownOnFailure().use{ scope ->
            scope.fork { println("B: First") }
            scope.fork { println("B: Second") }
            scope.join()
            scope.throwIfFailed()
        }

    }
}