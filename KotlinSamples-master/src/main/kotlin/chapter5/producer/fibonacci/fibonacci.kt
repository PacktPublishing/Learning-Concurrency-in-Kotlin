package chapter5.producer.fibonacci

import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.channels.take
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

val context = newSingleThreadContext("myThread")

/**
 * Fibonacci sequence using a producer.
 */
val fibonacci = produce(context) {
    send(1L)
    var current = 1L
    var next = 1L
    while (true) {
        send(next)
        val tmpNext = current + next
        current = next
        next = tmpNext
    }
}

fun main(args: Array<String>) = runBlocking {
    fibonacci.take(10).consumeEach {
        println(it)
    }
}


