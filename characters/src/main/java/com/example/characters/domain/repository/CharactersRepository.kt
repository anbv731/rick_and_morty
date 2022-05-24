package com.example.characters.domain.repository

import androidx.paging.PagingData
import com.example.characters.domain.CharacterDomain
import kotlinx.coroutines.flow.Flow


public interface CharactersRepository {
    suspend fun getDBCharacters(): List<CharacterDomain>
    suspend fun refreshCharacters()
     suspend fun getIdCharacters(id:Int):CharacterDomain
}