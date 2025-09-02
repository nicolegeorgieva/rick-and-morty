package com.example.rickandmorty.screen.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen() {
  val viewModel: HomeViewModel = hiltViewModel()

  HomeUi(
    uiState = viewModel.uiState(),
    onEvent = viewModel::onEvent,
  )
}

@Composable
fun HomeUi(
  uiState: HomeState,
  onEvent: (HomeEvent) -> Unit
) {
  // TODO
}