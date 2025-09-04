package com.example.rickandmorty.screen.characters

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

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
  // TODO
}
