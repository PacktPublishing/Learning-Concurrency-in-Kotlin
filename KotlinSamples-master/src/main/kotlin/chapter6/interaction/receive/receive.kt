package chapter6.interaction.receive

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ClosedReceiveChannelException
import kotlinx.coroutines.experimental.runBlocking

/**
 * Interaction with a ReceiveChannel
 */
fun main(args: Array<String>) = runBlocking {
    isClosedForReceive()
    receiveException()
    isEmpty()
}

fun isClosedForReceive() {
    val channel = Channel<Int>()
    channel.isClosedForReceive // false
    channel.close()
    channel.isClosedForReceive // true
}

suspend fun receiveException() {
    val channel = Channel<Int>()
    channel.close()

    try {
        channel.receive()
    } catch (e: ClosedReceiveChannelException) {
        println("ClosedReceiveChannelException handled")
    }
}

suspend fun isEmpty() {
    val channel = Channel<Int>(1)
    channel.isEmpty // true
    channel.send(10)
    channel.isEmpty // false
}