package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.common.ComposeViewModel
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.ui.characters.CharacterUi
import com.example.rickandmorty.ui.mapper.CharacterUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
  private val charactersRepository: CharactersRepository,
  private val characterUiMapper: CharacterUiMapper,
  private val savedStateHandle: SavedStateHandle,
) : ComposeViewModel<CharacterState, CharacterEvent>() {
  private var characterRes by mutableStateOf<Either<ErrorResponse, CharacterUi>?>(null)

  @Composable
  override fun uiState(): CharacterState {
    LaunchedEffect(Unit) {
      fetchCharacterInfo(id = )
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
}