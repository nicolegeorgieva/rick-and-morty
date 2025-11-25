package com.example.rickandmorty.data.episodes.datasource

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class EpisodesDto(
  val info: EpisodesInfoDto,
  val results: List<EpisodeDto>,
)

@Serializable
data class EpisodesInfoDto(
  val pages: Int,
  val next: String?,
)

@OptIn(ExperimentalTime::class)
@Serializable
data class EpisodeDto(
  val id: Int,
  val name: String,
  val airDate: String,
  val episode: String,
  val characters: List<String>,
  val url: String,
  @Contextual
  val created: Instant,
)