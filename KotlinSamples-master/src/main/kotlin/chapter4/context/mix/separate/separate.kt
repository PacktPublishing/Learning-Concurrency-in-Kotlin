package chapter4.context.mix.separate

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

/**
 * Use `minusKey()` to separate two contexts previously joined.
 */
fun main(args: Array<String>) = runBlocking {
    val dispatcher = newSingleThreadContext("myDispatcher")
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("Error captured")
        println("Message: ${throwable.message}")
    }

    // Combine two contexts together
    val context = dispatcher + handler

    // Remove one element from the context
    val tmpCtx = context.minusKey(dispatcher.key)

    launch(tmpCtx) {
        println("Running in ${Thread.currentThread().name}")
        TODO("Not implemented!")
    }.join()
}