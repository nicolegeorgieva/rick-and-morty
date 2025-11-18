package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterScreen() {
  val viewModel: CharacterViewModel = hiltViewModel()

  CharacterUi(
    uiState = viewModel.uiState(),
    onEvent = viewModel::onEvent,
  )
}

@Composable
fun CharacterUi(
  uiState: CharacterState,
  onEvent: (CharacterEvent) -> Unit
) {
  // TODO
}
