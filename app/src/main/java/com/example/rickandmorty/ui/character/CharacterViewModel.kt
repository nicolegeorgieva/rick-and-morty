package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.common.ComposeViewModel
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.repository.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.ui.characters.CharacterUi
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
  private var characterRes by mutableStateOf<Either<ErrorResponse, CharacterUi>?>(null)

  @Composable
  override fun uiState(): CharacterState {
    LaunchedEffect(Unit) {
      fetchCharacterInfo(id = screen.id)
    }

    return when (val character = characterRes) {
      null -> CharacterState.Loading
      is Either.Left<ErrorResponse> -> CharacterState.Error(
        message = errorMessageMapper.transformErrorToMessage(character.value)
      )

      is Either.Right<CharacterUi> -> CharacterState.Success(character = character.value)
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

  private fun fetchCharacterInfo(id: Int) {
    viewModelScope.launch {
      characterRes = charactersRepository.fetchCharacter(id).map { character ->
        characterUiMapper.map(character)
      }
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(screen: Screen.Character): CharacterViewModel
  }
}