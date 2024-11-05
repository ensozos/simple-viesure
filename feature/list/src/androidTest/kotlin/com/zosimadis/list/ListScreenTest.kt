package com.zosimadis.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.zosimadis.model.Book
import org.junit.Rule
import org.junit.Test

class ListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgress_whenLoadingState_isVisible() {
        composeTestRule.setContent {
            ListScreen(state = ListUiState.Loading, onBookClick = {})
        }

        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.list_loading),
        ).assertExists()
    }

    @Test
    fun bookList_whenSuccessState_isVisible() {
        composeTestRule.setContent {
            ListScreen(state = ListUiState.Success(books = books), onBookClick = {})
        }

        with(composeTestRule) {
            books.forEach { book ->
                onNodeWithText(book.title).assertTextEquals(book.title)
                onNodeWithText(book.description).assertTextEquals(book.description)
            }
        }
    }
}

private val books = listOf(
    Book(
        id = 0,
        title = "Realigned multimedia framework",
        description = "nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vitae mattis nibh ligula",
        imageUrl = "https://dummyimage.com/366x582.png/5fa2dd/ffffff",
        author = "ilias",
        releaseDate = 1233534L,
    ),
    Book(
        id = 1,
        title = "Devolved executive portal",
        description = "in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat",
        imageUrl = "https://dummyimage.com/415x133.png/cc0000/ffffff",
        author = "karim",
        releaseDate = 1573874L,
    ),
    Book(
        id = 2,
        title = "Re-engineered 24/7 capability",
        description = "in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo",
        imageUrl = "https://dummyimage.com/595x477.png/dddddd/000000",
        author = "mary",
        releaseDate = 1457896L,
    ),
)
