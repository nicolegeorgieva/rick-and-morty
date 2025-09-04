package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.ComposeViewModel
import com.example.rickandmorty.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val navigator: Navigator,
) : ComposeViewModel<CharactersState, CharactersEvent>() {
  @Composable
  override fun uiState(): CharactersState {
    return CharactersState()
  }

  override fun onEvent(event: CharactersEvent) {
    when (event) {
      CharactersEvent.BackClick -> handleBackClick()
    }
  }

  private fun handleBackClick() {
    viewModelScope.launch {
      navigator.back()
    }
  }
}