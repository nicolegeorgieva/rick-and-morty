package com.example.rickandmorty.data.episodes

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
  private val episodesDataSource: EpisodesDataSource,
  private val errorMapper: ErrorMapper,
) {
  suspend fun fetchEpisodes(page: Int?): Either<ErrorResponse, Episodes> {
    return episodesDataSource.fetchEpisodes(page)
      .mapLeft(errorMapper::mapError)
      .map { episodesDto ->
        Episodes(
          info = EpisodesInfo(
            pages = episodesDto.info.pages,
            next = episodesDto.info.next
          ),
          results = TODO()
        )
      }
  }
}