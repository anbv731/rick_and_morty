package com.example.characters.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.characters.domain.CharacterDomain

@Entity(tableName = "characters", indices = [Index(value = ["id"], unique = true)])
data class CharacterModel(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String
)

fun List<CharacterModel>.asDomainModel(): List<CharacterDomain> {
    return map {
        CharacterDomain(
            name = it.name,
            id = it.id
        )
    }
}