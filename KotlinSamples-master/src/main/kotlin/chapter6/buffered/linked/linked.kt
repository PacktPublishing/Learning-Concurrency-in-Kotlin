package chapter6.buffered.linked

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Creating a LinkedListChannel
 */
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>(Channel.UNLIMITED)
        val sender = launch {
            repeat(5) {
                println("Sending $it")
                channel.send(it)
            }
        }

        delay(500)
    }

    println("Took ${time}ms")
}