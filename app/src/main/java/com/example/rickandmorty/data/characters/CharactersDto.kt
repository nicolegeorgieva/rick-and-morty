package com.example.rickandmorty.data.characters

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CharactersDto(
  val info: CharactersInfoDto,
  val results: List<CharacterDto>,
)

@Serializable
class CharactersInfoDto(
  val pages: Int,
  val next: String?,
)

@Serializable
data class CharacterDto(
  val id: Int,
  val name: String,
  val status: String,
  val species: String,
  val type: String,
  val gender: String,
  val origin: OriginDto,
  val location: LocationDto,
  val image: String,
  val episode: List<String>,
  @Contextual
  val created: Instant,
)

@Serializable
data class OriginDto(
  val name: String,
  val url: String,
)

@Serializable
data class LocationDto(
  val name: String,
  val url: String,
)