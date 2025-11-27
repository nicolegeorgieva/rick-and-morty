package com.example.rickandmorty.ui.character

import androidx.compose.runtime.Immutable
import com.example.rickandmorty.ui.characters.CharacterUi

@Immutable
sealed interface CharacterState {
  data object Loading : CharacterState
  data class Success(val character: CharacterUi) : CharacterState
  data class Error(val message: String) : CharacterState
}