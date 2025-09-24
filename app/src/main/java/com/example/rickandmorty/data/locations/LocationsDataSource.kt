package com.example.rickandmorty.data.locations

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
    return Either.catch {
      httpClient.get(urlString = "location") {
        if (page != null) {
          parameter("page", page)
        }
      }.body<LocationsDto>()
    }
  }

  suspend fun fetchLocation(id: Int): Either<Throwable, LocationDto> {
    return Either.catch {
      httpClient.get(urlString = "location/$id").body<LocationDto>()
    }
  }
}