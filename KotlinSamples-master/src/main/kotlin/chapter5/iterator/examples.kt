package chapter5.iterator

import kotlin.coroutines.experimental.buildIterator

/**
 * Examples of the usage of an iterator.
 */
fun main(args: Array<String>) {
    yieldValues()
    buildingIterators()
    allElements()
    gettingNextValue()
    validatingForElements()
    notSuchElementException()
    yieldingNote()
}

fun yieldValues() {
    val iterator = buildIterator {
        yield("First")
        yield("Second")
        yield("Third")
    }

    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
}

fun buildingIterators() {
    val iterator = buildIterator {
        yield(1)
    }

    val iterator2 : Iterator<Any> = buildIterator {
        yield(1)
        yield(10L)
        yield("Hello")
    }
}

fun allElements() {
    val iterator = buildIterator {
        yield("First")
        yield("Second")
        yield("Third")
    }

    iterator.forEach {
        println(it)
    }
}

fun gettingNextValue() {
    val iterator : Iterator<Any> = buildIterator {
        yield(1)
        yield(10L)
        yield("Hello")
    }

    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
}

fun validatingForElements() {
    val iterator = buildIterator {
        for (i in 0..4) {
            yield(i * 4)
        }
    }

    for (i in 0..5) {
        if (iterator.hasNext()) {
            println("element $i is ${iterator.next()}")
        } else {
            println("No more elements")
        }
    }
}

fun notSuchElementException() {
    val iterator = buildIterator {
        yield(1)
    }

    println(iterator.next())
    // Uncomment this line to reproduce the error
    //println(iterator.next())
}

fun yieldingNote() {
    val iterator = buildIterator {
        println("yielding 1")
        yield(1)
        println("yielding 2")
        yield(2)
    }

    iterator.next()

    if (iterator.hasNext()) {
        println("iterator has next")
        iterator.next()
    }
}