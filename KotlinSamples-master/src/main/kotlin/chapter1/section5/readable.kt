package chapter1.section5

import kotlinx.coroutines.experimental.async

/**
 * This file contains logic that is asynchronous
 * but can be read easily.
 */

class Profile(userInfo: UserInfo, contactInfo: ContactInfo)
class UserInfo(name:String, lastName: String)
class ContactInfo(address: String, zipCode: Int)

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