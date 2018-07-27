package chapter8

import kotlinx.coroutines.experimental.*
import java.util.*

// TODO: Add the VM param `-Dkotlinx.coroutines.debug` in order
// to see the expected logs
fun main(args: Array<String>) = runBlocking {
    // Prints to the log using automatic naming
    automaticNaming()

    // Set a name for the coroutine
    specificNaming()

    // Set breakpoints inside the function and
    // add a variable to the debugger to see the
    // coroutine name
    breakpoints()

}

private fun threadName() : String {
    return Thread.currentThread().name
}

suspend fun automaticNaming() {
    val pool = newFixedThreadPoolContext(3, "myPool")
    val ctx = newSingleThreadContext("ctx")

    val tasks = mutableListOf<Deferred<Unit>>()
    for (i in 0..5) {
        val task = async(pool) {
            println("Processing $i in ${threadName()}")

            withContext(ctx) {
                println("Step two of $i happening in thread ${threadName()}")
            }

            println("Finishing $i in ${threadName()}")

        }

        tasks.add(task)
    }

    for (task in tasks) {
        task.await()
    }
}

suspend fun specificNaming(){
    val pool = newFixedThreadPoolContext(3, "myPool")
    val ctx = newSingleThreadContext("ctx")

    withContext(pool + CoroutineName("main")) {
        println("Running in ${threadName()}")

        withContext(ctx + CoroutineName("inner")) {
            println("Switching to ${threadName()}")
        }
    }
}

suspend fun breakpoints() {
    val pool = newFixedThreadPoolContext(3, "myPool")
    val ctx = newSingleThreadContext("ctx")

    for (i in 0..5) {
        async(pool + CoroutineName("main")) {
            // TODO: set conditional breakpoint in the line below
            val year = Calendar.getInstance().get(Calendar.YEAR)

            withContext(ctx + CoroutineName("inner")) {
                // TODO: Set a breakpoint in the line below and use the
                // watch to see the current value
                println(year)
            }
        }.await()
    }
}