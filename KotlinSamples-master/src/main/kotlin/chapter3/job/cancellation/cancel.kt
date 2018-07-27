package chapter3.job.cancellation

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * This code cancels a Job without a cause
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        // Do some work here
        delay(5000)
    }

    // timeout for the operation
    delay(2000)
    job.cancel()
}