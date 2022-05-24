package com.example.characters.domain

import com.squareup.moshi.Json

data class CharacterDomain(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val created: String,
    val image: String)