package com.example.characters.domain.repository

import com.example.characters.domain.CharacterDomain

public interface CharactersRepository {
    suspend fun getDBCharacters(): List<CharacterDomain>
    suspend fun refreshCharacters()
}