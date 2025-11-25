package com.example.rickandmorty.data.episodes

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class EpisodesDataSource @Inject constructor(
  private val httpClient: HttpClient,
) {
  suspend fun fetchEpisodes(page: Int?): Either<Throwable, EpisodesDto> {
    return Either.catch {
      httpClient.get("episode") {
        if (page != null) {
          parameter("page", page)
        }
      }.body<EpisodesDto>()
    }
  }

  suspend fun fetchEpisode(id: Int): Either<Throwable, EpisodeDto> {
    return Either.catch {
      httpClient.get("episode/$id").body<EpisodeDto>()
    }
  }
}