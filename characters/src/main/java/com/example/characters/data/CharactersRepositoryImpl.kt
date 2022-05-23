package com.example.characters.data


import android.content.Context
import androidx.paging.PagingData
import com.example.characters.data.database.asDomainModel
import com.example.characters.data.database.getDatabase
import com.example.characters.data.network.RetrofitClient
import com.example.characters.data.network.asModel
import com.example.characters.domain.CharacterDomain
import com.example.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val context:Context,
) : CharactersRepository {
    private val database = getDatabase(context)


    override suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            val characters = RetrofitClient().getApi().getData().results
            database.charactersDao.insertAll(characters.asModel())
        }
    }

    override suspend fun getDBCharacters(): List<CharacterDomain> {
        return database.charactersDao.getCharacters().asDomainModel()
        }

    override fun searchCharacters(): Flow<List<CharacterDomain>> {
        TODO("Not yet implemented")
    }
    }


//    @OptIn(ExperimentalPagingApi::class)
//    private fun loadAllCharacters(): Pager<Int, Character> {
//        val pager = Pager(
//            config = PagingConfig(
//                enablePlaceholders = true,
//                pageSize = 20
//            ),
//            remoteMediator = CharacterRemoteMediator(db, apiService)
//        ) {
//            db.charactersDao().getWholeList()
//        }
//        return pager
//    }

