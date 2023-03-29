package dev.sundeep.stepchallenge.domain

import dev.sundeep.stepchallenge.domain.entity.User
import junit.framework.TestCase.*
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserUnitTest {

    @Test
    fun testEquals() {
        val user1 = User("1", "Alice", 25, "alice@example.com", false)
        val user2 = User("1", "Bob", 30, "bob@example.com", true)
        val user3 = User("2", "Alice", 25, "alice@example.com", false)

        assertEquals(user1, user2) // Check that two users with the same id are considered equal
        assertEquals(user1, user1) // Check that a user is equal to itself
        assertEquals(user1, User("1", "Alice", 25, "jane@example.com", true)) // Check that email and isAdmin are not taken into account when comparing users
        assertNotEquals(user1, user3) // Check that two users with different ids are not equal
    }

    @Test
    fun testHashCode() {
        val user1 = User("1", "Alice", 25, "alice@example.com", false)
        val user2 = User("1", "Bob", 30, "bob@example.com", true)

        assertEquals(user1.hashCode(), user2.hashCode()) // Check that two users with the same id have the same hash code
    }
}