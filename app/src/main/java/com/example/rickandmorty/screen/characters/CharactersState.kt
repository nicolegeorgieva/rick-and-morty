package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface CharactersState {
  data object Loading : CharactersState
  data class Success(
    val characters: CharactersUi,
    val loadingNewCharacters: Boolean
  ) : CharactersState

  data class Error(val message: String) : CharactersState
}

data class CharactersUi(
  val info: CharactersInfoUi,
  val results: List<CharacterUi>,
)

data class CharactersInfoUi(
  val pages: Int,
  val next: String?,
)

@Immutable
data class CharacterUi(
  val id: String,
  val name: String,
  val status: CharacterStatusUi,
  val species: CharacterSpeciesUi,
  val type: String,
  val gender: CharacterGenderUi,
  val origin: OriginUi,
  val location: LocationUi,
  val image: String,
  val episode: ImmutableList<String>,
  val created: String,
)

@Immutable
enum class CharacterStatusUi {
  Alive,
  Dead,
  Unknown,
}

@Immutable
enum class CharacterSpeciesUi {
  Human,
  Animal,
  Alien,
  Other,
}

@Immutable
enum class CharacterGenderUi {
  Female,
  Male,
  Genderless,
  Unknown,
}

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