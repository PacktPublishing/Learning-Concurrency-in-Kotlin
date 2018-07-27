package chapter8

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import java.util.Calendar

/**
 * This file defines code to be tested with
 * functional tests. Please see the tests packages.
 */
data class User(
        val name: String,
        val age: Int,
        val profession: String
)

class UserManager(private val dataSource: DataSource) {
    suspend fun getUser(id: Int): User {
        val name = dataSource.getNameAsync(id)
        val age = dataSource.getAgeAsync(id)
        val profession = dataSource.getProfessionAsync(id)

        // Wait for each of them, don't assume they are ready
        return User(name.await(),
                age.await(),
                profession.await())
    }
}

interface DataSource {
    fun getNameAsync(id: Int): Deferred<String>
    fun getAgeAsync(id: Int): Deferred<Int>
    fun getProfessionAsync(id: Int): Deferred<String>
}