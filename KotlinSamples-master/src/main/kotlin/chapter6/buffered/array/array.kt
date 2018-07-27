package chapter6.buffered.array

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.take
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Creating an ArrayChannel
 */
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>(4)
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }

        delay(500)

        println("Taking two")
        channel.take(2).receive()

        delay(500)
    }

    println("Took ${time}ms")
}