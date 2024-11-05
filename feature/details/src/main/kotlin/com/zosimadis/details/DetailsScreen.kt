package com.zosimadis.details

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zosimadis.designsystem.theme.SimpleProjectTheme

@Composable
internal fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state: DetailsUiState by viewModel.detailsUiState.collectAsStateWithLifecycle()

    DetailsScreen(state = state)
}

@Composable
internal fun DetailsScreen(
    modifier: Modifier = Modifier,
    state: DetailsUiState,
) {
    Box(modifier = modifier.fillMaxSize()) {
        val loadingDescription = stringResource(R.string.details_loading)
        when (state) {
            DetailsUiState.Error -> Toast.makeText(
                LocalContext.current,
                "Error",
                Toast.LENGTH_SHORT,
            ).show()

            DetailsUiState.Loading -> CircularProgressIndicator(
                modifier = modifier
                    .semantics {
                        contentDescription = loadingDescription
                    }
                    .align(Alignment.Center),
            )

            is DetailsUiState.Success -> DetailContent(
                imageUrl = state.imageUrl,
                title = state.title,
                description = state.description,
                author = state.author,
                releaseDate = state.releaseDate,
            )
        }
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String,
    author: String,
    releaseDate: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            model = imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )

        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = releaseDate,
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodySmall,
        )

        Text(
            text = description,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "Author: $author",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    SimpleProjectTheme {
        DetailContent(
            imageUrl = "https://dummyimage.com/366x582.png/5fa2dd/ffffff",
            title = "Realigned multimedia framework",
            description = "nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vitae mattis nibh ligula",
            author = "ilias",
            releaseDate = "Wed, Jul 8, '20",
        )
    }
}
