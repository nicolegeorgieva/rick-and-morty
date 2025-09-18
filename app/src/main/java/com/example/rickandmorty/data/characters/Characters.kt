package com.example.rickandmorty.data.characters

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Characters(
  val info: CharactersInfo,
  val results: List<Character>,
)

data class CharactersInfo(
  val pages: Int,
  val next: String?
)

@OptIn(ExperimentalTime::class)
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
  val created: Instant,
)

data class Origin(
  val name: String,
  val url: String,
)

data class Location(
  val name: String,
  val url: String,
)