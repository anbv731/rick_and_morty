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
    val id: Int,
    @Json(name="status")
    val status:String,
    @Json(name="species")
    val species:String,
    @Json(name="gender")
    val gender:String,
    @Json(name="created")
    val created:String,
    @Json(name="image")
    val image:String,

)
fun List<CharacterDto>.asModel(): List<CharacterModel> {
    return map {
        CharacterModel(
            name = it.name,
            id = it.id,
            status = it.status,
            species = it.species,
            created = it.created,
            image = it.image,
            gender = it.gender
        )
    }
}