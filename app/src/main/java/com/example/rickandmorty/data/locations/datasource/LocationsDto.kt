package com.example.rickandmorty.data.locations.datasource

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class LocationsDto(
  val info: LocationsInfoDto,
  val results: List<LocationDto>,
)

@Serializable
data class LocationsInfoDto(
  val pages: Int,
  val next: String?,
)

@Serializable
data class LocationDto(
  val id: Int,
  val name: String,
  val type: String,
  val dimension: String,
  val residents: List<String>,
  @Contextual
  val created: Instant,
)