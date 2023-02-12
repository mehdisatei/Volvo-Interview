package mehdi.satei.volvo.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import mehdi.satei.volvo.presentation.screen.home.HomeScreen
import mehdi.satei.volvo.presentation.screen.home.HomeState
import mehdi.satei.volvo.presentation.theme.VolvoTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenErrorTest() {
        composeTestRule.setContent {
            VolvoTheme {
                HomeScreen(
                    state = HomeState(
                        weatherList = listOf(),
                        isLoading = false,
                        error = "error"
                    ),
                    toggleTheme = {

                    },
                ) {}
            }
        }

        composeTestRule.onNodeWithText("error").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertDoesNotExist()
    }

    @Test
    fun homeScreenLoadingTest() {
        composeTestRule.setContent {
            VolvoTheme {
                HomeScreen(
                    state = HomeState(
                        weatherList = listOf(),
                        isLoading = true,
                        error = null
                    ),
                    toggleTheme = {

                    },
                ) {}
            }
        }

        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }
}