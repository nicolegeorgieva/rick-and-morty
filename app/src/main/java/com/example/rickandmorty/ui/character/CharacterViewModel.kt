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
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.ui.characters.CharacterUi
import com.example.rickandmorty.ui.mapper.CharacterUiMapper
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
) : ComposeViewModel<CharacterState, CharacterEvent>() {
  private var characterRes by mutableStateOf<Either<ErrorResponse, CharacterUi>?>(null)

  @Composable
  override fun uiState(): CharacterState {
    LaunchedEffect(Unit) {
      fetchCharacterInfo(id = screen.id)
    }

    return CharacterState()
  }

  override fun onEvent(event: CharacterEvent) {
    // TODO
  }

  private fun fetchCharacterInfo(id: Int) {
    viewModelScope.launch {
      characterRes = charactersRepository.fetchCharacter(id)
        .map { character ->
          characterUiMapper.map(character)
        }
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(screen: Screen.Character): CharacterViewModel
  }
}