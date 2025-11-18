package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.navigation.Screen

@Composable
fun CharacterScreen(screen: Screen.Character) {
  val viewModel = hiltViewModel<CharacterViewModel, CharacterViewModel.Factory> {
    it.create(screen)
  }

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
