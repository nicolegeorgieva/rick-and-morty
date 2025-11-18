package com.example.rickandmorty.ui.mapper

import com.example.rickandmorty.data.characters.Character
import com.example.rickandmorty.ui.characters.CharacterGenderUi
import com.example.rickandmorty.ui.characters.CharacterSpeciesUi
import com.example.rickandmorty.ui.characters.CharacterStatusUi
import com.example.rickandmorty.ui.characters.CharacterUi
import com.example.rickandmorty.ui.characters.LocationUi
import com.example.rickandmorty.ui.characters.OriginUi
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class CharacterUiMapper @Inject constructor() {
  fun map(character: Character): CharacterUi {
    return CharacterUi(
      id = character.id,
      name = character.name,
      status = when (character.status) {
        "Alive" -> CharacterStatusUi.Alive
        "Dead" -> CharacterStatusUi.Dead
        else -> CharacterStatusUi.Unknown
      },
      species = when (character.species) {
        "Human" -> CharacterSpeciesUi.Human
        "Animal" -> CharacterSpeciesUi.Animal
        "Alien" -> CharacterSpeciesUi.Alien
        else -> CharacterSpeciesUi.Other
      },
      type = character.type,
      gender = when (character.gender) {
        "Female" -> CharacterGenderUi.Female
        "Male" -> CharacterGenderUi.Male
        "Genderless" -> CharacterGenderUi.Genderless
        else -> CharacterGenderUi.Unknown
      },
      origin = OriginUi(
        name = character.name,
        url = character.origin.url,
      ),
      location = LocationUi(
        name = character.location.name,
        url = character.location.url,
      ),
      image = character.image,
      episode = character.episode.toImmutableList(),
      created = ""
    )
  }
}