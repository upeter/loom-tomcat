package org.up.loom

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class ThreadsVsVirtualThreads {
    @Test
    fun `many Threads`() {
        measure {
            val threads = (1..1_000_000).toList().map {
                thread {
                    sleep(2000)
                    print(".")
                }
            }
            println("Virtual Threads: Ready to Roll")
            threads.forEach {
                it.join()
            }
        }
    }

    @Test
    fun `many Coroutines`() = measure {
        runBlocking {
            (1..1_000_000).toList().map {
                launch {
                    delay(2000)
                    print(".")
                }
            }
        }
        println("Coroutines: Ready to Roll")
    }

    @Test
    fun `many virtual Threads`() {
        measure {
            val threads = (1..1_000_000).toList().map {
                Thread.startVirtualThread {
                    sleep(2000)
                    print(".")
                }
            }
            println("Virtual Threads: Ready to Roll")
            threads.forEach {
                it.join()
            }
        }
    }
}


fun <T> measure(block: () -> T): T {
    val start = System.currentTimeMillis()
    return block().also {
        println("Took: ${System.currentTimeMillis() - start} ms")
    }
}