package com.example.data

import com.example.data.network.NetworkService
import com.example.data.network.RetrofitClient
import com.example.domain.CharacterDomain
import com.example.domain.repository.CharactersRepository

class CharactersRepositoryImpl: CharactersRepository {
    override suspend fun getCharacters(): List<CharacterDomain> {
        return RetrofitClient().getApi().getData()
    }
}