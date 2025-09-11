package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface CharactersState {
  data object Loading : CharactersState
  data class Success(val characters: ImmutableList<Character>) : CharactersState
  data class Error(val message: String) : CharactersState
}

data class CharactersUi(
  val info: CharactersInfoUi,
  val results: List<CharacterUi>,
)

data class CharactersInfoUi(
  val pages: Int,
)

@Immutable
data class CharacterUi(
  val id: String,
  val name: String,
  val status: String,
  val species: String,
  val type: String,
  val gender: String,
  val origin: OriginUi,
  val location: LocationUi,
  val image: String,
  val episode: ImmutableList<String>,
  val created: String,
)

@Immutable
data class OriginUi(
  val name: String,
  val url: String,
)

@Immutable
data class LocationUi(
  val name: String,
  val url: String,
)