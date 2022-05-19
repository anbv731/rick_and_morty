package com.example.data.network

import com.example.data.database.CharacterModel 
import com.squareup.moshi.Json
data class Response constructor(
    @Json(name="results")
    val results: List<CharacterDto>,
)
data class CharacterDto constructor(
    @Json(name = "name")
    val name: String,
    @Json(name = "id")
    val id: String
) {
    companion object {
        fun dtoToModel(characterDto: CharacterDto): CharacterModel {
            return CharacterModel(
                name = characterDto.name,
                id = characterDto.id
            )
        }
    }
}
fun List<CharacterDto>.asModel(): List<CharacterModel> {
    return map {
        CharacterModel(
            name = it.name,
            id = it.id
        )
    }
}