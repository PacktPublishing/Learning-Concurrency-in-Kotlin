package chapter3.job.lazy.start

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * This code start a coroutine lazily but doesn't wait
 * for it to complete, so the log entry is never printed.
 */
fun main(args: Array<String>) {
    val job = launch(start = CoroutineStart.LAZY) {
        delay(3000)
        println("job completed")
    }

    job.start()
}
