package chapter3.deferred.exception.await.trycatch

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * In this example with handle an exception by using a
 * try-catch block
 */
fun main(args: Array<String>) = runBlocking {
    val deferred = async {
        TODO("Not implemented yet!")
    }

    try {
        deferred.await()
    } catch (throwable: Throwable) {
        println("Deferred cancelled due to ${throwable.message}")
    }
}