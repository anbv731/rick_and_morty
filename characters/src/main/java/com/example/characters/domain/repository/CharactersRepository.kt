package com.example.characters.domain.repository

import com.example.characters.domain.model.CharacterDomain


interface CharactersRepository {
    suspend fun getDBCharacters(): List<CharacterDomain>
    suspend fun refreshCharacters()
}