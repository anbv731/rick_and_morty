package com.example.characters.domain.usecases

import com.example.characters.domain.CharacterDomain
import com.example.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetDBCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend fun execute(): List<CharacterDomain> {
        return repository.getDBCharacters()
    }
}