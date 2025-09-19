package com.example.rickandmorty.screen.characters

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.component.RickAndMortyTopAppBar
import com.example.rickandmorty.screen.characters.component.CharacterCard
import com.example.rickandmorty.screen.characters.component.Error
import com.example.rickandmorty.screen.characters.component.Loading

@Composable
fun CharactersScreen() {
  val viewModel: CharactersViewModel = hiltViewModel()

  CharactersUi(
    uiState = viewModel.uiState(),
    onEvent = viewModel::onEvent,
  )
}

@VisibleForTesting
@Composable
fun CharactersUi(
  uiState: CharactersState,
  onEvent: (CharactersEvent) -> Unit
) {
  Scaffold(
    topBar = {
      RickAndMortyTopAppBar(
        title = stringResource(R.string.home_characters),
        navigateBack = true,
        onBackClick = {
          onEvent(CharactersEvent.BackClick)
        },
      )
    }
  ) { padding ->
    val listState = rememberLazyListState()

    if (uiState is CharactersState.Success) {
      LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
          .collect { layoutInfo ->
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = layoutInfo.totalItemsCount

            if (lastVisible >= totalItems - 5 && !uiState.loadingNewCharacters) {
              onEvent(CharactersEvent.LoadNewPage)
            }
          }
      }
    }

    LazyColumn(
      modifier = Modifier
        .padding(padding)
        .padding(horizontal = 12.dp),
      state = listState,
    ) {
      when (uiState) {
        is CharactersState.Error -> item(key = "error") { Error(message = uiState.message) }
        CharactersState.Loading -> item(key = "loading") { Loading() }
        is CharactersState.Success -> {
          itemsIndexed(
            items = uiState.characters.results,
            key = { index, _ ->
              "character-$index"
            }
          ) { index, character ->
            CharacterCard(
              character = character,
              onLocationClick = {
                onEvent(CharactersEvent.LocationClick(it))
              }
            )
            Spacer(Modifier.height(24.dp))
          }

          if (uiState.loadingNewCharacters) {
            item(key = "loading-new-characters-indicator") {
              LoadingNewCharactersIndicator()
            }
          }
        }
      }
    }
  }
}

@Composable
private fun LoadingNewCharactersIndicator(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}