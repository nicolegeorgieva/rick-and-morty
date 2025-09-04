package com.example.rickandmorty.screen.characters

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.component.RickAndMortyTopAppBar
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
    LazyColumn(
      modifier = Modifier.padding(padding)
    ) {
      when (uiState) {
        is CharactersState.Error -> item(key = "error") { Error(message = uiState.message) }
        CharactersState.Loading -> item(key = "loading") { Loading() }
        is CharactersState.Success -> items(uiState.characters) { character ->
          Text(character.name)
          Spacer(Modifier.height(12.dp))
        }
      }
    }
  }
}