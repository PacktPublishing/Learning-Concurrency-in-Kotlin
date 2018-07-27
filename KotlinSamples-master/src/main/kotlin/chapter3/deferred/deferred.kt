package chapter3.deferred

import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * Examples of how to instantiate a Deferred
 */
fun main(args: Array<String>) = runBlocking {
    // Create a Deferred using `async`
    val headlines = async {
        getHeadlines()
    }

    // Wait for it to complete
    headlines.await()

    // Create a Deferred using the factory function
    val deferred = CompletableDeferred<Unit>()
}

fun getHeadlines() {
    // Nothing to do here
}

// Dummy class
class Article

// A deferred can be created using the constructor as well
val articles = CompletableDeferred<List<Article>>()