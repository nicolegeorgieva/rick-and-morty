package com.example.rickandmorty.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.component.RickAndMortyTopAppBar

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
  RickAndMortyTopAppBar(text = stringResource(R.string.home_title))
}