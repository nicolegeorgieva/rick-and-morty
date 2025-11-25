package com.example.rickandmorty.data.locations.repository

import kotlin.time.Instant

data class Locations(
  val info: LocationsInfo,
  val results: List<Location>,
)

data class LocationsInfo(
  val pages: Int,
  val next: String?,
)

data class Location(
  val id: Int,
  val name: String,
  val type: String,
  val dimension: String,
  val residents: List<String>,
  val created: Instant,
)