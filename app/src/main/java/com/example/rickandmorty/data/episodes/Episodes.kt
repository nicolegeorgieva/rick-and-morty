package com.example.rickandmorty.data.episodes

import kotlin.time.Instant

data class Episodes(
  val info: EpisodesInfo,
  val results: List<Episode>,
)

data class EpisodesInfo(
  val pages: Int,
  val next: String?,
)

data class Episode(
  val id: Int,
  val name: String,
  val airDate: String,
  val episode: String,
  val characters: List<String>,
  val url: String,
  val created: Instant,
)