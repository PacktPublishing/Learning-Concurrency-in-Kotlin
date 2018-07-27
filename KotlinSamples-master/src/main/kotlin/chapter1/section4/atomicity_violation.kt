package chapter1.section4

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

// This method will often print values lower than 2100
fun main(args: Array<String>) = runBlocking {
    val workerA = asyncIncrement(2000)
    val workerB = asyncIncrement(100)

    workerA.await()
    workerB.await()

    print("counter [$counter]")
}

var counter = 0

fun asyncIncrement(by: Int) = async {
    for (i in 0 until by) {
        counter++
    }
}