package chapter4.client.suspending

import kotlinx.coroutines.experimental.runBlocking

/**
 * Implementation using suspending functions.
 */
fun main(args: Array<String>) = runBlocking {
    val repository: ProfileServiceRepository = ProfileServiceClient()

    val profile = repository.fetchById(12)
    println(profile)
}

data class Profile(val id: Long, val name: String, val age: Int)

interface ProfileServiceRepository {
    suspend fun fetchByName(name: String) : Profile
    suspend fun fetchById(id: Long) : Profile
}

class ProfileServiceClient : ProfileServiceRepository {
    override suspend fun fetchByName(name: String) : Profile {
        return Profile(1, name, 28)
    }

    override suspend fun fetchById(id: Long) : Profile {
        return Profile(id, "Susan", 28)
    }

}