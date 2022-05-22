package com.example.characters.domain.usecases

import com.example.characters.domain.CharacterDomain
import com.example.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class SearchDBCharacters @Inject constructor(private val repository: CharactersRepository) {
    suspend fun execute(request: String): List<CharacterDomain> {
        return repository.searchCharacters(request)
    }
}
