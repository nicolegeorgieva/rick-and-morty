package com.example.rickandmorty.ui.character

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.component.RickAndMortyTopAppBar
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.ui.characters.component.Error
import com.example.rickandmorty.ui.characters.component.Loading

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
  Scaffold(
    topBar = {
      RickAndMortyTopAppBar(
        title = stringResource(R.string.character_title),
        navigateBack = true,
        onBackClick = {
          onEvent(CharacterEvent.BackClick)
        },
      )
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier
        .padding(padding)
        .padding(horizontal = 16.dp),
    ) {
      when (uiState) {
        is CharacterState.Error -> item(key = "error") { Error(message = uiState.message) }
        CharacterState.Loading -> item(key = "loading") { Loading() }
        is CharacterState.Success -> {
          item(key = "character-info") {
            Text(uiState.character.name)
          }
        }
      }
    }
  }
}