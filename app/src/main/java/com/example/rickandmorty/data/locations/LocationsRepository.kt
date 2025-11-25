package com.example.rickandmorty.data.locations

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import javax.inject.Inject

class LocationsRepository @Inject constructor(
  private val locationsDataSource: LocationsDataSource,
  private val errorMapper: ErrorMapper,
  private val locationMapper: LocationMapper,
) {
  suspend fun fetchLocations(page: Int?): Either<ErrorResponse, Locations> {
    return locationsDataSource.fetchLocations(page)
      .mapLeft(errorMapper::mapError)
      .map { locationsDto ->
        Locations(
          info = LocationsInfo(
            pages = locationsDto.info.pages,
            next = locationsDto.info.next,
          ),
          results = locationsDto.results.map { locationDto ->
            locationMapper.map(locationDto)
          },
        )
      }
  }

  suspend fun fetchLocation(id: Int): Either<ErrorResponse, Location> {
    return locationsDataSource.fetchLocation(id)
      .mapLeft(errorMapper::mapError)
      .map { locationDto ->
        locationMapper.map(locationDto)
      }
  }
}