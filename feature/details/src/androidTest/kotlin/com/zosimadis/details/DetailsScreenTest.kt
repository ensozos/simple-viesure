package com.zosimadis.details

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgressIndicator_whenLoadingState_isVisible() {
        composeTestRule.setContent {
            DetailsScreen(state = DetailsUiState.Loading)
        }

        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.details_loading))
            .assertExists()
    }

    @Test
    fun bookContent_whenSuccessState_isVisible() {
        composeTestRule.setContent {
            DetailsScreen(state = successState)
        }

        with(composeTestRule) {
            onNodeWithText("Realigned multimedia framework").assertExists()
            onNodeWithText("1 Sept 2000").assertExists()
        }
    }
}

private val successState = DetailsUiState.Success(
    imageUrl = "https://dummyimage.com/366x582.png/5fa2dd/ffffff",
    title = "Realigned multimedia framework",
    description = "nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vitae mattis nibh ligula",
    author = "Ilias Zosimadis",
    releaseDate = "1 Sept 2000",
)
