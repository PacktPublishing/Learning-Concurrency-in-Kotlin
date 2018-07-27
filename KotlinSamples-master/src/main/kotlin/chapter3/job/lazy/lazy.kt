package chapter3.job.lazy

import kotlinx.coroutines.experimental.*

/**
 * This code creates a Job that will not be started
 * automatically. Notice that since the Job is never
 * actually started, the exception is not propagated
 */
fun main(args: Array<String>) = runBlocking {
    launch(start = CoroutineStart.LAZY) {
        TODO("Not implemented yet!")
    }

    delay(500)
}