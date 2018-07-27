package chapter7.confinement

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

// This implementation executes all the increments in the same
// thread, so it will always increase up to 2100
fun main(args: Array<String>) = runBlocking {
    val workerA = asyncIncrement(2000)
    val workerB = asyncIncrement(100)

    workerA.await()
    workerB.await()

    print("counter [$counter]")
}

var counter = 0
val context = newSingleThreadContext("counter")

fun asyncIncrement(by: Int) = async(context) {
    for (i in 0 until by) {
        counter++
    }
}