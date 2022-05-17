package com.example.data.network

import com.example.data.CharacterModel
import com.example.domain.CharacterDomain
import retrofit2.http.GET

interface NetworkService  {
    @GET("character")
    suspend fun getData(): List<CharacterDomain>
}