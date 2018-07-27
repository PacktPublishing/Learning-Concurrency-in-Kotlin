package chapter6.interaction.send

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ClosedSendChannelException
import kotlinx.coroutines.experimental.channels.take
import kotlinx.coroutines.experimental.runBlocking

/**
 * Interaction with a SendChannel
 */
fun main(args: Array<String>) = runBlocking {
    isClosed()
    isFull()
    send()
    sendException()
    offerException()
    offerWhenFull()
    offer()
}

fun isClosed() {
    val channel = Channel<Int>()
    channel.isClosedForSend // false
    channel.close()
    channel.isClosedForSend // true
}

suspend fun isFull() {
    val channel = Channel<Int>(1)
    channel.isFull // false
    channel.send(1)
    channel.isFull // true
}

suspend fun send() {
    val channel = Channel<Int>(1)
    channel.send(1)
}

suspend fun sendException() {
    val channel = Channel<Int>(1)
    channel.close()

    try {
        channel.send(1)
    } catch (e : ClosedSendChannelException) {
        println("ClosedSendChannelException handled")
    }

}

fun offerException() {
    val channel = Channel<Int>(1)
    channel.close()

    try {
        channel.offer(10)
    } catch (e : ClosedSendChannelException) {
        println("ClosedSendChannelException handled")
    }
}

suspend fun offerWhenFull() {
    val channel = Channel<Int>(1)
    channel.send(1)
    channel.offer(2) // false
}

suspend fun offer() {
    val channel = Channel<Int>(1)
    channel.offer(2) // false
    channel.receive() // 2
}