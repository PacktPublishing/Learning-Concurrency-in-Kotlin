package chapter3.job.cancellation.cancelled.exception.handler

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Handle the exception in a Job using
 * a CoroutineExceptionHandler
 */
fun main(args: Array<String>) = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
        _: CoroutineContext, throwable: Throwable ->
        println("Job cancelled due to ${throwable.message}")
    }

    launch(exceptionHandler) {
        TODO("Not implemented yet!")
    }

    delay(2000)
}