package chapter1.section1

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

import kotlin.system.measureTimeMillis

/**
 * Init, suspend, and resume three coroutines. They will
 * potentially be resumed in different threads than the ones
 * they were created on.
 */
fun main(args: Array<String>) = runBlocking {
    println("${Thread.activeCount()} threads active at the start")
    val time = measureTimeMillis {
        createCoroutines(3)
    }
    println("${Thread.activeCount()} threads active at end")
    println("Took $time ms")
}

suspend fun createCoroutines(amount: Int) {
    val jobs = ArrayList<Job>()
    for (i in 1..amount) {
        jobs += launch {
            println("Started $i in ${Thread.currentThread().name}")
            delay(1000)

            println("Finished $i in ${Thread.currentThread().name}")
        }
    }
    jobs.forEach {
        it.join()
    }
}