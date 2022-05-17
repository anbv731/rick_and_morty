package com.example.domain.repository

import com.example.domain.CharacterDomain

interface CharactersRepository {
    suspend fun getCharacters(): List<CharacterDomain>
}