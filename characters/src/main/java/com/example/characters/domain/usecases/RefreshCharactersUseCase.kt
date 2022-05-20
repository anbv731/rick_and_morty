package com.example.characters.domain.usecases

import com.example.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class RefreshCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend fun execute() {
        return repository.refreshCharacters()
    }
}