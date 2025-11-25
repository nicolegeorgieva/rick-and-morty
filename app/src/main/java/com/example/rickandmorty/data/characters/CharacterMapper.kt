package com.example.rickandmorty.data.characters

import javax.inject.Inject

class CharacterMapper @Inject constructor() {
  fun map(characterDto: CharacterDto): Character {
    return Character(
      id = characterDto.id,
      name = characterDto.name,
      status = characterDto.status,
      species = characterDto.species,
      type = characterDto.type,
      gender = characterDto.gender,
      origin = Origin(
        name = characterDto.origin.name,
        url = characterDto.origin.url,
      ),
      location = Location(
        name = characterDto.location.name,
        url = characterDto.location.url,
      ),
      image = characterDto.image,
      episode = characterDto.episode,
      created = characterDto.created,
    )
  }
}