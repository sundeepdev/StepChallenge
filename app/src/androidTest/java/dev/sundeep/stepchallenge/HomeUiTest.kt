package dev.sundeep.stepchallenge

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.ui.users.UserList
import dev.sundeep.stepchallenge.ui.users.UsersListUiState
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dev.sundeep.stepchallenge", appContext.packageName)
    }

    @Test
    fun loadUserListWithAdmin_expectAdminLabelToShow() {

        // Arrange
        rule.setContent {
            UserList(
                uiState = UsersListUiState.Success(getUserListWithAdmin())
            )
        }

        // Act

        // Assert
        rule.onNodeWithText("John Doe").assertExists()
        rule.onNodeWithText("john.doe@example.com").assertExists()
        rule.onNodeWithText("Admin").assertExists()
    }

    @Test
    fun loadUserListWithoutAdmin_expectAdminLabelToNotShow() {

        // Arrange
        rule.setContent {
            UserList(
                uiState = UsersListUiState.Success(getUserListWithoutAdmin())
            )
        }

        // Act

        // Assert
        rule.onNodeWithText("John Doe").assertExists()
        rule.onNodeWithText("john.doe@example.com").assertExists()
        rule.onNodeWithText("Admin").assertDoesNotExist()
    }

    private fun getUserListWithAdmin(): List<User> {
        return listOf(
            User("1", "John Doe", 25, "john.doe@example.com", false),
            User("2", "Jane Smith", 30, "jane.smith@example.com", true),
            User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
        )
    }

    private fun getUserListWithoutAdmin(): List<User> {
        return listOf(
            User("1", "John Doe", 25, "john.doe@example.com", false),
            User("2", "Jane Smith", 30, "jane.smith@example.com", false),
            User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
        )
    }
}