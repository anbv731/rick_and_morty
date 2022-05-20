package com.example.characters.data.network

import com.example.characters.data.database.CharacterModel
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
)
fun List<CharacterDto>.asModel(): List<CharacterModel> {
    return map {
        CharacterModel(
            name = it.name,
            id = it.id
        )
    }
}