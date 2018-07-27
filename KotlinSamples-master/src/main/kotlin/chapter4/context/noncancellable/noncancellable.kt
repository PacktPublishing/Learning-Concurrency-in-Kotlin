package chapter4.context.noncancellable

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

/**
 * Examples using cancellation.
 */
fun main(args: Array<String>) = runBlocking {
    cancellation()
    cancellationDelay()
    nonCancellable()
}

suspend fun cancellation() {
    val duration = measureTimeMillis {
        val job = launch {
            try {
                while (isActive) {
                    delay(500)
                    println("still running")
                }
            } finally {
                println("cancelled, will end now")
            }
        }

        delay(1200)
        job.cancelAndJoin()
    }

    println("Took $duration ms")
}

suspend fun cancellationDelay() {
    val duration = measureTimeMillis {
        val job = launch {
            try {
                while (isActive) {
                    delay(500)
                    println("still running")
                }
            } finally {
                println("cancelled, will delay finalization now")
                delay(5000)
                println("delay completed, bye bye")
            }
        }

        delay(1200)
        job.cancelAndJoin()
    }

    println("Took $duration ms")
}

suspend fun nonCancellable() {
    val duration = measureTimeMillis {
        val job = launch {
            try {
                while (isActive) {
                    delay(500)
                    println("still running")
                }
            } finally {
                withContext(NonCancellable) {
                    println("cancelled, will delay finalization now")
                    delay(5000)
                    println("delay completed, bye bye")
                }
            }
        }

        delay(1200)
        job.cancelAndJoin()
    }

    println("Took $duration ms")
}