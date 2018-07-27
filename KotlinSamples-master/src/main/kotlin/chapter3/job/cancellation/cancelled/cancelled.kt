package chapter3.job.cancellation.cancelled

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch {
        delay(5000)
    }

    delay(2000)

    // cancel
    job.cancel(cause = Exception("Tired of waiting"))

    val cancellation = job.getCancellationException()
    cancellation.cause // Exception("Tired of waiting")
}