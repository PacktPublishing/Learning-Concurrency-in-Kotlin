package chapter4.context.switch

import kotlinx.coroutines.experimental.*

/**
 * Switch context using `withContext` instead of the complete
 * coroutine builder
 */
fun main(args: Array<String>) = runBlocking {
    before()
    after()
}

suspend fun before() {
    val dispatcher = newSingleThreadContext("myThread")
    val name = async(dispatcher) {
        // Do important operation here
        "Susan Calvin"
    }.await()

    println("User: $name")
}

suspend fun after() {
    val dispatcher = newSingleThreadContext("myThread")
    val name = withContext(dispatcher) {
        // Do important operation here
        "Susan Calvin"
    }

    println("User: $name")
}