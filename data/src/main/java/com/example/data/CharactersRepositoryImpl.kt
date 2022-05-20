package com.example.data


import android.content.Context
import com.example.data.database.asDomainModel
import com.example.data.database.getDatabase
import com.example.data.network.RetrofitClient
import com.example.data.network.asModel
import com.example.domain.CharacterDomain
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val context:Context,
) : CharactersRepository {
    private val database = getDatabase(context)


    override suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            val characters = RetrofitClient().getApi().getData().results
            database.charactersDao.insertAll(characters.asModel())
        }
    }

    override  suspend fun getDBCharacters(): List<CharacterDomain> {

        return database.charactersDao.getCharacters().asDomainModel()
    }

}