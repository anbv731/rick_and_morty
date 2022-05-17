package com.example.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.util.TableInfo
import com.squareup.moshi.Json

@Entity(tableName = "characters", indices = [Index(value = ["id"], unique = true)])
data class CharacterModel(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?
)