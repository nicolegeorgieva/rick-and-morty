package com.example.rickandmorty.data.episodes.repository

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.episodes.datasource.EpisodesDataSource
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
  private val episodesDataSource: EpisodesDataSource,
  private val errorMapper: ErrorMapper,
  private val episodeMapper: EpisodeMapper,
) {
  suspend fun fetchEpisodes(page: Int?): Either<ErrorResponse, Episodes> {
    return episodesDataSource.fetchEpisodes(page)
      .mapLeft(errorMapper::map)
      .map { episodesDto ->
        Episodes(
          info = EpisodesInfo(
            pages = episodesDto.info.pages,
            next = episodesDto.info.next
          ),
          results = episodesDto.results.map { episodeDto ->
            episodeMapper.map(episodeDto)
          }
        )
      }
  }

  suspend fun fetchEpisode(id: Int): Either<ErrorResponse, Episode> {
    return episodesDataSource.fetchEpisode(id)
      .mapLeft(errorMapper::map)
      .map { episodeDto ->
        episodeMapper.map(episodeDto)
      }
  }
}