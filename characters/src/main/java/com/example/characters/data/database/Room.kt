package com.example.characters.data.database

import androidx.room.*
import android.content.Context



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
