package chapter5.iterator.fibonacci

import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.buildIterator

/**
 * Implementation of a Fibonacci sequence using
 * an iterator.
 */
fun main(args: Array<String>) = runBlocking {
    val fibonacci = buildIterator {
        yield(1L)
        var current = 1L
        var next = 1L
        while (true) {
            yield(next)
            val tmpNext = current + next
            current = next
            next = tmpNext
        }
    }

    for (i in 0..91) {
        println("$i is ${fibonacci.next()}")
    }
}

