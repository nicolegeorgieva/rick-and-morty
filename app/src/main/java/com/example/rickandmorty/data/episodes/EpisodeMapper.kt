package com.example.rickandmorty.data.episodes

import javax.inject.Inject

class EpisodeMapper @Inject constructor() {
  fun map(episodeDto: EpisodeDto): Episode {
    return Episode(
      id = episodeDto.id,
      name = episodeDto.name,
      airDate = episodeDto.airDate,
      episode = episodeDto.episode,
      characters = episodeDto.characters,
      url = episodeDto.url,
      created = episodeDto.created
    )
  }
}