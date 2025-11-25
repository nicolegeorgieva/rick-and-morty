package com.example.rickandmorty.data.locations.datasource

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class LocationsDataSource @Inject constructor(
  private val httpClient: HttpClient,
) {
  suspend fun fetchLocations(page: Int?): Either<Throwable, LocationsDto> {
    return Either.Companion.catch {
      httpClient.get("location") {
        if (page != null) {
          parameter("page", page)
        }
      }.body<LocationsDto>()
    }
  }

  suspend fun fetchLocation(id: Int): Either<Throwable, LocationDto> {
    return Either.Companion.catch {
      httpClient.get("location/$id").body<LocationDto>()
    }
  }
}