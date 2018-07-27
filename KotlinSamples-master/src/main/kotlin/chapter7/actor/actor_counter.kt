package chapter7.actor

import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.newSingleThreadContext

/**
 * The contents of this classes are used in
 * `actor.kt`
 */
private var counter = 0
private val context = newSingleThreadContext("actor")

enum class Action {
    INCREASE,
    DECREASE
}

fun getCounter() = counter

val actorCounter = actor<Action>(context) {
    for (msg in channel) {
        when(msg) {
            Action.INCREASE -> counter++
            Action.DECREASE -> counter--
        }
    }
}