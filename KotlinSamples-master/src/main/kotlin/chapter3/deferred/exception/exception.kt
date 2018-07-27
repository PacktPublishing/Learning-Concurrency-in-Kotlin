package chapter3.deferred.exception

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

/**
 * In this example the exception will not be propagated
 * because `await()` isn't being called
 */
fun main(args: Array<String>) = runBlocking {
    val deferred = async {
        TODO("Not implemented yet!")
    }

    // Wait for it to fail
    delay(2000)
}