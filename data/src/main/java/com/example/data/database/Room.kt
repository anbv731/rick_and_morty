package com.example.data.database

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
//@Database(entities = [CharacterModel::class], version = 1, exportSchema = true)
//abstract class CharactersDataBase: RoomDatabase() {
//    abstract val charactersDao: CharactersDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: CharactersDataBase? = null
//
//        fun getDatabase(context: Context): CharactersDataBase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        CharactersDataBase::class.java,
//                        "characters_database"
//                    ).build()
//
//                    INSTANCE = instance
//                }
//
//                return instance
//            }
//        }
//    }
//}