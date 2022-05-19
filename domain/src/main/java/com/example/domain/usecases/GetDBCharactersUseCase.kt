package com.example.domain.usecases

import com.example.domain.CharacterDomain
import com.example.domain.repository.CharactersRepository

class GetDBCharactersUseCase(private val repository: CharactersRepository) {
    suspend fun execute(): List<CharacterDomain> {
        return repository.getDBCharacters()
    }
}