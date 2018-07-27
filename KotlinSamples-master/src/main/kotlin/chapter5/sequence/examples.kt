package chapter5.sequence

import kotlin.coroutines.experimental.buildSequence

/**
 * Interacting with sequences.
 */
fun main(args: Array<String>) {
    simpleSequences()
    readingAllTheValues()
    elementAt()
    elementAtOrElse()
    elementAtOrNull()
    groupOfElements()
    stateless()
}

fun simpleSequences() {
    val sequence = buildSequence {
        yield(1)
    }

    val sequence2 : Sequence<Any> = buildSequence {
        yield("A")
        yield(1)
        yield(32L)
    }
}

val sequence = buildSequence {
    yield(1)
    yield(1)
    yield(2)
    yield(3)
    yield(5)
    yield(8)
    yield(13)
    yield(21)
}

fun readingAllTheValues() {
    sequence.forEach {
        print("$it ")
    }

    println()
    sequence.forEachIndexed { index, value ->
        println("element at $index is $value")
    }
}

fun elementAt() {
    println(sequence.elementAt(4))
}

fun elementAtOrElse() {
    println(sequence.elementAtOrElse(10, { it * 2 }))
}

fun elementAtOrNull() {
    println(sequence.elementAtOrNull(10))
}

fun groupOfElements() {
    val firstFive = sequence.take(5)
    println(firstFive.joinToString())
}

fun stateless() {
    val sequence = buildSequence {
        for (i in 0..9) {
            println("Yielding $i")
            yield(i)
        }
    }

    println("Requesting index 1")
    sequence.elementAt(1)

    println("Requesting index 2")
    sequence.elementAt(2)

    println("Taking 3")
    sequence.take(3).joinToString()
}