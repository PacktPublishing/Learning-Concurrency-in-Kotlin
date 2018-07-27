package chapter2.launch

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

// Use laynch to start a coroutine that crashes.
fun main(args: Array<String>) = runBlocking {

    val task = launch {
        doSomething()
    }

    task.join()
    println("completed")
}

suspend fun doSomething() {
    delay(100)
    println("Half-way to crash")
    delay(100)
    throw UnsupportedOperationException("Can't do")
}