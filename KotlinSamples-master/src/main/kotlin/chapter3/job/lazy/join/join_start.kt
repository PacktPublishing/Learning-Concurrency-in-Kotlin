package chapter3.job.lazy.join

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * This code start a job lazily, and waits for its execution to complete
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        delay(3000)
    }

    job.join()
}