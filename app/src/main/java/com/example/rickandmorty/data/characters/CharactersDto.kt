package com.example.rickandmorty.data.characters

import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
  val info: CharactersInfoDto,
  val results: List<Character>,
)

@Serializable
data class CharactersInfoDto(
  val pages: Int,
)

@Serializable
data class Character(
  val id: String,
  val name: String,
  val status: String,
  val species: String,
  val type: String,
  val gender: String,
  val origin: Origin,
  val location: Location,
  val image: String,
  val episode: List<String>,
  val created: String,
)

@Serializable
data class Origin(
  val name: String,
  val url: String,
)

@Serializable
data class Location(
  val name: String,
  val url: String,
)