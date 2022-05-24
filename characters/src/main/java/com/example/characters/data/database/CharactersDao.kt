package com.example.characters.data.database

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("select * from characters")
    fun getCharacters(): List<CharacterModel>

    @Query("select * from characters WHERE id LIKE :id")
    fun getIdCharacters(id:Int):CharacterModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterModel>)
}