package chapter8

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.Calendar
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Functional tets for the chapter 8.
 */
class SampleAppFT {

    @Test
    fun testHappyPath() = runBlocking {
        val manager = UserManager(MockDataSource())

        val user = manager.getUser(10)
        assertTrue { user.name == "Susan Calvin" }
        assertTrue { user.age == Calendar.getInstance().get(Calendar.YEAR) - 1982 }
        assertTrue { user.profession == "Robopsychologist" }
    }

    @Test
    fun testOppositeOrder() = runBlocking {
        val manager = UserManager(MockSlowDbDataSource())

        val user = manager.getUser(10)
        assertTrue { user.name == "Susan Calvin" }
        assertTrue { user.age == Calendar.getInstance().get(Calendar.YEAR) - 1982 }
        assertTrue { user.profession == "Robopsychologist" }
    }
}

// Mock a datasource that retrieves the data in a different order
class MockSlowDbDataSource: DataSource {
    // Mock getting the name from the database
    override fun getNameAsync(id: Int) = async {
        delay(1000)
        "Susan Calvin"
    }

    // Mock getting the age from the cache
    override fun getAgeAsync(id: Int) = async {
        delay(500)
        Calendar.getInstance().get(Calendar.YEAR) - 1982
    }

    // Mock getting the profession from an external system
    override fun getProfessionAsync(id: Int) = async {
        delay(200)
        "Robopsychologist"
    }
}

// Mock a datasource that retrieves the data in the expected order
class MockDataSource: DataSource {
    // Mock getting the name from the database
    override fun getNameAsync(id: Int) = async {
        delay(200)
        "Susan Calvin"
    }

    // Mock getting the age from the cache
    override fun getAgeAsync(id: Int) = async {
        delay(500)
        Calendar.getInstance().get(Calendar.YEAR) - 1982
    }

    // Mock getting the profession from an external system
    override fun getProfessionAsync(id: Int) = async {
        delay(2000)
        "Robopsychologist"
    }
}