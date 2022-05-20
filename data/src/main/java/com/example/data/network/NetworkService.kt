package com.example.data.network

import com.example.domain.CharacterDomain
import retrofit2.http.GET

interface NetworkService  {
    @GET("character")
    suspend fun getData(): Response
}