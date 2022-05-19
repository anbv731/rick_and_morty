package com.example.domain.usecases

import com.example.domain.CharacterDomain
import com.example.domain.repository.CharactersRepository

class RefreshCharactersUseCase(private val repository: CharactersRepository) {
    suspend fun execute(){
        return repository.refreshCharacters()
    }
}