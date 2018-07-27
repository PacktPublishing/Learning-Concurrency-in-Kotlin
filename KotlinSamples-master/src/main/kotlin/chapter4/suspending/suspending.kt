package chapter4.suspending

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

/**
 * Basic suspending function.
 */
fun main(args: Array<String>) {
    runBlocking {
        greetDelayed(1000)
    }
}

suspend fun greetDelayed(delayMillis: Int) {
    delay(delayMillis)
    println("Hello, World!")
}