package com.example.characters.data.repository


import android.content.Context
import com.example.characters.data.database.asListDomainModel
import com.example.characters.data.database.getDatabase
import com.example.characters.data.network.RetrofitClient
import com.example.characters.data.network.asModel
import com.example.characters.domain.model.CharacterDomain
import com.example.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val context: Context,
) : CharactersRepository {
    private val database = getDatabase(context)


    override suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            val characters = RetrofitClient().getApi().getData().results
            database.charactersDao.insertAll(characters.asModel())
        }
    }

    override suspend fun getDBCharacters(): List<CharacterDomain> {
        return database.charactersDao.getCharacters().asListDomainModel()
    }
}


