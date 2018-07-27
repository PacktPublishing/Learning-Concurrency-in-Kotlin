package chapter4.client.deferred

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * Implementation using deferred.
 */
fun main(args: Array<String>) = runBlocking {
    val client : ProfileServiceRepository = ProfileServiceClient()

    val profile = client.asyncFetchById(12).await()
    println(profile)
}

data class Profile(val id: Long, val name: String, val age: Int)

interface ProfileServiceRepository {
    fun asyncFetchByName(name: String) : Deferred<Profile>
    fun asyncFetchById(id: Long) : Deferred<Profile>
}

class ProfileServiceClient : ProfileServiceRepository {
    override fun asyncFetchByName(name: String) = async {
        Profile(1, name, 28)
    }

    override fun asyncFetchById(id: Long) = async {
        Profile(id, "Susan", 28)
    }
}