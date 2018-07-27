package chapter4.context.dispatcher

import kotlinx.coroutines.experimental.*

/**
 * This file contains examples of different types of dispatchers
 */
fun main(args: Array<String>) {
    commonPool()
    defaultDispatcher()
    unconfined()
    singleThread()
    threadPool()
}

fun commonPool() = runBlocking {
    launch(CommonPool) {
        println("Running in ${Thread.currentThread().name}")
    }.join()
}

fun defaultDispatcher() = runBlocking {
    launch {
        println("Running in ${Thread.currentThread().name}")
    }.join()

    launch(DefaultDispatcher) {
        println("Running in ${Thread.currentThread().name}")
    }.join()
}

fun unconfined() = runBlocking {
    launch(Unconfined) {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Resuming in ${Thread.currentThread().name}")
    }.join()
}

fun singleThread() = runBlocking {
    val dispatcher = newSingleThreadContext("myThread")

    launch(dispatcher) {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Resuming in ${Thread.currentThread().name}")
    }.join()
}

fun threadPool() = runBlocking {
    val dispatcher = newFixedThreadPoolContext(4, "myPool")

    launch(dispatcher) {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Resuming in ${Thread.currentThread().name}")
    }.join()
}