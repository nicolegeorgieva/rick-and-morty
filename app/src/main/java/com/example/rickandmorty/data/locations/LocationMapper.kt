package com.example.rickandmorty.data.locations

import javax.inject.Inject

class LocationMapper @Inject constructor() {
  fun map(locationDto: LocationDto): Location {
    return Location(
      id = locationDto.id,
      name = locationDto.name,
      type = locationDto.type,
      dimension = locationDto.dimension,
      residents = locationDto.residents,
      created = locationDto.created
    )
  }
}