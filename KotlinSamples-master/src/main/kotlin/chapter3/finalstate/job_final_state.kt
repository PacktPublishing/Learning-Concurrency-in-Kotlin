package chapter3.finalstate

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * This code tries to restart a Job by calling start() once
 * it has reached a final state. Notice that calling start()
 * has no effect since the state of a Job can only move forward
 */
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val job = launch {
            delay(2000)
        }

        // Wait for it to complete once
        job.join()

        // Restart the Job
        job.start()
        job.join()
    }

    println("Took $time ms")
}