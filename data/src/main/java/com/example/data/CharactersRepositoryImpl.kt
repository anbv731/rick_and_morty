package com.example.data

import android.app.Application
import android.content.Context
import com.example.data.database.CharactersDataBase
import com.example.data.database.asDomainModel
import com.example.data.database.getDatabase
import com.example.data.network.CharacterDto
import com.example.data.network.NetworkService
import com.example.data.network.RetrofitClient
import com.example.data.network.asModel
import com.example.domain.CharacterDomain
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val context:Context,

   //private val database: CharactersDataBase
) : CharactersRepository {

    override suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            val characters = RetrofitClient().getApi().getData()
            getDatabase(context).charactersDao.insertAll(characters.asModel())
        }
    }

    override suspend fun getCharacters(): List<CharacterDomain> {
        return RetrofitClient().getApi().getData().asModel().asDomainModel()
    }
}