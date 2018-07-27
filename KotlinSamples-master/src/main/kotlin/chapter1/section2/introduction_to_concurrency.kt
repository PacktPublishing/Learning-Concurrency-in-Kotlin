package chapter1.section2

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

class Profile(userInfo: UserInfo, contactInfo: ContactInfo)
class UserInfo(name:String, lastName: String)
class ContactInfo(address: String, zipCode: Int)

// Example of a sequential implementation, where getUserInfo blocks
// the thread to do an IO operation
class Sequential {
    fun getProfile(id: Int): Profile {
        val basicUserInfo = getUserInfo(id)
        val contactInfo = getContactInfo(id)

        return createProfile(basicUserInfo, contactInfo)
    }

    private fun getUserInfo(id: Int): UserInfo {
        // Block the thread for one second
        // to simulate a service call
        Thread.sleep(1000)

        return UserInfo("Susan", "Calvin")
    }

    private fun getContactInfo(id: Int): ContactInfo {
        // Block the thread for one second
        // to simulate a service call
        Thread.sleep(1000)
        return ContactInfo("False Street 123", 11111)
    }

    private fun createProfile(userInfo: UserInfo, contactInfo: ContactInfo): Profile {
        return Profile(userInfo, contactInfo)
    }
}

// Concurrent implementation using suspending functions and
// asynchronous code
class Concurrent {
    suspend fun getProfile(id: Int): Profile {
        val basicUserInfo = asyncGetUserInfo(id)
        val contactInfo = asyncGetContactInfo(id)

        return createProfile(basicUserInfo.await(), contactInfo.await())
    }

    private fun asyncGetUserInfo(id: Int) = async {
        // Block the thread for one second
        // to simulate a service call
        Thread.sleep(1000)

        UserInfo("Susan", "Calvin")
    }

    private fun asyncGetContactInfo(id: Int) = async {
        // Block the thread for one second
        // to simulate a service call
        Thread.sleep(1000)
        ContactInfo("False Street 123", 11111)
    }

    private fun createProfile(userInfo: UserInfo, contactInfo: ContactInfo): Profile {
        return Profile(userInfo, contactInfo)
    }
}

// Measuring their execution
fun main(args: Array<String>) {
    val sequentialTime = measureTimeMillis {
        val sequential = Sequential()
        sequential.getProfile(1)
    }

    val concurrentTime = measureTimeMillis {
        runBlocking {
            val concurrent = Concurrent()
            concurrent.getProfile(1)
        }
    }

    println("Sequential took $sequentialTime ms")
    println("Concurrent took $concurrentTime ms")
}