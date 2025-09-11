package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.ComposeViewModel
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val navigator: Navigator,
  private val charactersRepository: CharactersRepository,
) : ComposeViewModel<CharactersState, CharactersEvent>() {
  private var charactersRes by mutableStateOf<Either<String, List<Character>>?>(null)

  @Composable
  override fun uiState(): CharactersState {
    LaunchedEffect(Unit) {
      fetchCharacters()
    }

    return when (val characters = charactersRes) {
      null -> CharactersState.Loading
      is Either.Left<String> -> CharactersState.Error(message = characters.value)
      is Either.Right<List<Character>> -> CharactersState.Success(characters.value.toImmutableList())
    }
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

  private fun fetchCharacters() {
    viewModelScope.launch {
      charactersRes =
        Either.Right(
          listOf(
            Character(
              id = "1",
              name = "Amy"
            ),
            Character(
              id = "2",
              name = "Lilly"
            ),
            Character(
              id = "3",
              name = "Victoria"
            )
          )
        )
      delay(2000)
      charactersRes = Either.Left("No internet connection")
    }
  }
}