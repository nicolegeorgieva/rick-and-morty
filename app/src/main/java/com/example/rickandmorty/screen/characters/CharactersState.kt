package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface CharactersState {
  data object Loading : CharactersState
  data class Success(val characters: ImmutableList<Character>) : CharactersState
  data class Error(val message: String) : CharactersState
}

@Immutable
data class Character(
  val id: String,
  val name: String,
)