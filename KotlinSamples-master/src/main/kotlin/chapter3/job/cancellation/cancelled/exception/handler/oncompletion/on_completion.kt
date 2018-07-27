package chapter3.job.cancellation.cancelled.exception.handler.oncompletion

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * Handle the exception of a Job using `invokeOnCompletion`
 */
fun main(args: Array<String>) = runBlocking {
    launch {
        TODO("Not implemented yet!")
    }.invokeOnCompletion { cause ->
        cause?.let {
            println("Job cancelled due to ${it.message}")
        }
    }

    delay(2000)
}

