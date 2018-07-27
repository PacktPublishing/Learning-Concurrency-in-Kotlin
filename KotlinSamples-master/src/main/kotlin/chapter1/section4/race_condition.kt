package chapter1.section4

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

data class UserInfo(val name: String, val lastName: String, val id: Int)

lateinit var user: UserInfo

/**
 * This will crash because the information is not ready by the
 * time we try to use it.
 */

fun main(args: Array<String>) = runBlocking {
    asyncGetUserInfo(1)
    // Do some other operations
    delay(1000)

    println("User ${user.id} is ${user.name}")
}

fun asyncGetUserInfo(id: Int) = async {
    delay(1100)
    user = UserInfo(id = id, name = "Susan", lastName = "Calvin")
}