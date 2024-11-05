package com.zosimadis.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zosimadis.designsystem.theme.SimpleProjectTheme
import com.zosimadis.model.Book

@Composable
internal fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel(),
    onBookClick: (Int) -> Unit,
) {
    val state: ListUiState by viewModel.listUiState.collectAsStateWithLifecycle()

    ListScreen(
        modifier = modifier,
        state = state,
        onBookClick = onBookClick,
    )
}

@Composable
internal fun ListScreen(
    modifier: Modifier = Modifier,
    state: ListUiState,
    onBookClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        val loadingContentDescription = stringResource(R.string.list_loading)
        when (state) {
            ListUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.semantics {
                    contentDescription = loadingContentDescription
                },
            )

            is ListUiState.Success -> BookList(books = state.books, onBookClick = onBookClick)
            is ListUiState.Error -> {
                Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()
                BookList(books = state.fallbackData ?: emptyList(), onBookClick = onBookClick)
            }
        }
    }
}

@Composable
private fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book> = emptyList(),
    onBookClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(books) { book ->
            BookRow(
                modifier = Modifier
                    .clickable { onBookClick(book.id) }
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                imgUrl = book.imageUrl,
                title = book.title,
                description = book.description,
            )
        }
    }
}

@Composable
private fun BookRow(
    imgUrl: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .semantics { contentDescription = imgUrl }
                .padding(8.dp)
                .size(64.dp)
                .clip(CircleShape),
            error = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            model = imgUrl,
            contentDescription = null,
        )

        Column {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Clip,
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    SimpleProjectTheme {
        BookRow(
            imgUrl = "https://dummyimage.com/366x582.png/5fa2dd/ffffff",
            title = "Realigned multimedia framework",
            description = "nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vitae mattis nibh ligula",
        )
    }
}

@Preview
@Composable
private fun RowPreview() {
    SimpleProjectTheme {
        BookList(
            books = listOf(
                Book(
                    id = 0,
                    title = "Realigned multimedia framework",
                    description = "nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vitae mattis nibh ligula",
                    imageUrl = "https://dummyimage.com/366x582.png/5fa2dd/ffffff",
                    author = "ilias",
                    releaseDate = 1233534L,
                ),
            ),
            onBookClick = {},
        )
    }
}
