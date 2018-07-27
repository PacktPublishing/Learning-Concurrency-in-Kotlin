package chapter4.context.mix.join

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

/**
 * Join two contexts using the `+` operator
 */
fun main(args: Array<String>) = runBlocking {
    val dispatcher = newSingleThreadContext("myDispatcher")
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("Error captured")
        println("Message: ${throwable.message}")
    }

    // Combine two contexts together
    val context = dispatcher + handler

    launch(context) {
        println("Running in ${Thread.currentThread().name}")
        TODO("Not implemented!")
    }.join()
}