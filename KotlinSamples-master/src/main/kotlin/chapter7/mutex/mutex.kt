package chapter7.mutex

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock

// This implementation locks the code that modifies counter
// so that it's only executed by one coroutine at the time.
// This implementation will always increase counter to the
// correct value `2100`.
fun main(args: Array<String>) = runBlocking {
    val workerA = asyncIncrement(2000)
    val workerB = asyncIncrement(100)

    workerA.await()
    workerB.await()

    print("counter [$counter]")
}

var counter = 0
var mutex = Mutex()

fun asyncIncrement(by: Int) = async {
    for (i in 0 until by) {
        mutex.withLock {
            counter++
        }
    }
}