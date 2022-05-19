package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import com.example.data.network.CharacterDto
import com.example.domain.CharacterDomain
import com.squareup.moshi.Json

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