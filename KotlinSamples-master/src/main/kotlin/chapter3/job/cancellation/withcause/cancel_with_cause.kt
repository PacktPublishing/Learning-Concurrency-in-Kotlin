package chapter3.job.cancellation.withcause

import kotlinx.coroutines.experimental.*

/**
 * This passes a cause when cancelling a Job
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        // Do some work here
        delay(5000)
    }

    delay(2000)

    // cancel with a cause
    job.cancel(cause = Exception("Timeout!"))

    job.getCancellationException()
}