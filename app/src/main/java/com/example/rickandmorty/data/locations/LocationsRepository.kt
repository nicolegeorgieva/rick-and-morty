package com.example.rickandmorty.data.locations

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class LocationsRepository @Inject constructor(
  private val locationsDataSource: LocationsDataSource,
  private val errorMapper: ErrorMapper,
) {
  @OptIn(ExperimentalTime::class)
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
            Location(
              id = locationDto.id,
              name = locationDto.name,
              type = locationDto.type,
              dimension = locationDto.dimension,
              residents = locationDto.residents,
              created = locationDto.created
            )
          },
        )
      }
  }

  @OptIn(ExperimentalTime::class)
  suspend fun fetchLocation(id: Int): Either<ErrorResponse, Location> {
    return locationsDataSource.fetchLocation(id)
      .mapLeft(errorMapper::mapError)
      .map { locationDto ->
        Location(
          id = locationDto.id,
          name = locationDto.name,
          type = locationDto.type,
          dimension = locationDto.dimension,
          residents = locationDto.residents,
          created = locationDto.created
        )
      }
  }
}