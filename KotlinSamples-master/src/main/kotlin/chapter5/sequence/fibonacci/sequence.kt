package chapter5.sequence.fibonacci

import kotlin.coroutines.experimental.buildSequence

// Fibonacci sequence using a sequence.
fun main(args: Array<String>) {

    val fibonacci = buildSequence {
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

    println(fibonacci.elementAt(1))
    println(fibonacci.elementAt(2))
    val indexed = fibonacci.take(50).withIndex()

    for ((index, value) in indexed) {
        println("$index: $value")
    }


}