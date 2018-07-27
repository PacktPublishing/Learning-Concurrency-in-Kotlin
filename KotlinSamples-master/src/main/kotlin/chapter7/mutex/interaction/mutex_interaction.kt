package chapter7.mutex.interaction

import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock

// Interacting with a mutex.
fun main(args: Array<String>) = runBlocking {
    useWithLock()
    manualLock()
    isLocked()
    tryLockFalse()
    tryLockTrue()
}

suspend fun useWithLock() {
    val mutex = Mutex()
    mutex.withLock {
        // This will be atomic
    }
}

suspend fun manualLock() {
    val mutex = Mutex()

    mutex.lock()
    print("I am now an atomic block")
    mutex.unlock()
}

suspend fun isLocked() {
    val mutex = Mutex()

    mutex.lock()
    mutex.isLocked // true
    mutex.unlock()
}

suspend fun tryLockFalse() {
    val mutex = Mutex()
    mutex.lock()
    val lockedByMe = mutex.tryLock() // false
    mutex.unlock()
}

fun tryLockTrue() {
    val mutex = Mutex()

    val lockedByMe = mutex.tryLock() // true
    mutex.unlock()
}