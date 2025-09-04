package com.example.rickandmorty.screen.characters

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.component.RickAndMortyTopAppBar

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
    LazyColumn(
      modifier = Modifier.padding(padding)
    ) {

    }
  }
}
