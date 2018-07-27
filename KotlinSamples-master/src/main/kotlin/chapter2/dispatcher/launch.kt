package chapter2.dispatcher

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

// Use a ThreadPoolDispatcher to run the coroutine
fun main(args: Array<String>) = runBlocking {
    val netDispatcher = newSingleThreadContext(name = "ServiceCall")

    val task = launch(netDispatcher) {
        printCurrentThread()
    }

    task.join()
}

fun printCurrentThread() {
    println("Running in thread [${Thread.currentThread().name}]")
}

