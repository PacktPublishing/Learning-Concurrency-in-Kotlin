package chapter3.job

import kotlinx.coroutines.experimental.*

/**
 * This code instantiates a Job by using launch
 * and another by using the factory function. Notice
 * that in both cases they will be started automatically
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        TODO("Not implemented yet!")
    }

    val job2 = Job()

    delay(500)
}