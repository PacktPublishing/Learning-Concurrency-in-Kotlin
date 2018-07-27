package co.starcarr.rssreader.search

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.newSingleThreadContext

enum class Action {
    INCREASE,
    RESET
}

object ResultsCounter {
    private val context = newSingleThreadContext("counter")
    private var counter = 0
    private val notifications = Channel<Int>(Channel.CONFLATED)

    private val actor = actor<Action>(context) {
        for (msg in channel) {
            when (msg) {
                Action.INCREASE -> counter++
                Action.RESET -> counter = 0
            }

            notifications.send(counter)
        }
    }

    fun getNotificationChannel() : ReceiveChannel<Int> = notifications


    suspend fun increment() = actor.send(Action.INCREASE)

    suspend fun reset() = actor.send(Action.RESET)

}