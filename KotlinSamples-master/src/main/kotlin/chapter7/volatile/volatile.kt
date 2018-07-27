package chapter7.volatile

// Example of the correct usage of the annotation.
class DataProcessor {
    @Volatile
    private var shutdownRequested = false

    fun shutdown() {
        shutdownRequested = true
    }

    fun process() {
        while (!shutdownRequested) {
            // process away
        }
    }
}