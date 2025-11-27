package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.common.ComposeViewModel
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.repository.Character
import com.example.rickandmorty.data.characters.repository.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.ui.mapper.CharacterUiMapper
import com.example.rickandmorty.utils.ErrorMessageMapper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CharacterViewModel.Factory::class)
class CharacterViewModel @AssistedInject constructor(
  private val charactersRepository: CharactersRepository,
  private val characterUiMapper: CharacterUiMapper,
  @Assisted
  private val screen: Screen.Character,
  private val navigator: Navigator,
  private val errorMessageMapper: ErrorMessageMapper,
) : ComposeViewModel<CharacterState, CharacterEvent>() {

  private val characterFlow = charactersRepository.fetchCharacter(screen.id)

  @Composable
  override fun uiState(): CharacterState {
    val characterRes by characterFlow.collectAsState(initial = null)

    return when (
      val character = characterRes) {
      null -> CharacterState.Loading
      is Either.Left<ErrorResponse> -> CharacterState.Error(
        message = errorMessageMapper.transformErrorToMessage(character.value)
      )

      is Either.Right<Character> -> CharacterState.Success(
        character = characterUiMapper.map(character.value)
      )
    }
  }

  override fun onEvent(event: CharacterEvent) {
    when (event) {
      CharacterEvent.BackClick -> handleBackClick()
    }
  }

  private fun handleBackClick() {
    viewModelScope.launch {
      navigator.back()
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(screen: Screen.Character): CharacterViewModel
  }
}