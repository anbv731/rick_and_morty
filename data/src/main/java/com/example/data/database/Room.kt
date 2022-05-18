package com.example.data.database

import android.arch.persistence.room.*
import android.content.Context
import androidx.lifecycle.LiveData

@Dao
interface CharactersDao {
    @Query("select * from characters")
    fun getCharacters(): LiveData<List<CharacterModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterModel>)
}

@Database(entities = [CharacterModel::class], version = 1)
abstract class CharactersDataBase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
}

private lateinit var INSTANCE: CharactersDataBase

fun getDatabase(context: Context): CharactersDataBase {
    synchronized(CharactersDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CharactersDataBase::class.java,
                "characters"
            ).build()
        }
    }
    return INSTANCE
}