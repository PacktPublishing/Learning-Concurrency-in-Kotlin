package chapter7.actor

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

// This method will use an actor to print the correct value always
fun main(args: Array<String>) = runBlocking {
    val workerA = asyncIncrement(2000)
    val workerB = asyncIncrement(100)
    val workerC = asyncDecrement(1000)

    workerA.await()
    workerB.await()
    workerC.await()

    print("counter [${getCounter()}]")
}

fun asyncIncrement(by: Int) = async {
    for (i in 0 until by) {
        actorCounter.send(Action.INCREASE)
    }
}

fun asyncDecrement(by: Int) = async(CommonPool) {
    for (i in 0 until by) {
        actorCounter.send(Action.DECREASE)
    }
}