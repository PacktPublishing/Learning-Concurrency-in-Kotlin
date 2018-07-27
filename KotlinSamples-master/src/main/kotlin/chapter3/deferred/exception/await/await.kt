package chapter3.deferred.exception.await

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

/**
 * In this example the exception will be propagated because we
 * are calling await()
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val deferred = async {
        TODO("Not implemented yet!")
    }

    // Let it fail
    deferred.await()
}