package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Composable
import com.example.rickandmorty.ComposeViewModel

class CharactersViewModel() : ComposeViewModel<CharactersState, CharactersEvent>() {
  @Composable
  override fun uiState(): CharactersState {
    return CharactersState()
  }

  override fun onEvent(event: CharactersEvent) {
    // TODO
  }
}