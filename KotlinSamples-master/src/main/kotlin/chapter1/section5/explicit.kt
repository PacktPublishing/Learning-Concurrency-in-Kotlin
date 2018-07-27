package chapter1.section5

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * In this example, `async()` is being used to indicate
 * explicitly that it's an asynchronous operation.
 */
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val name = async { getName() }
        val lastName = async { getLastName() }

        println("Hello, ${name.await()} ${lastName.await()}")
    }

    println("Execution took $time ms")
}

suspend fun getName(): String {
    delay(1000)
    return "Susan"
}

suspend fun getLastName(): String {
    delay(1000)
    return "Calvin"
}