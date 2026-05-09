package com.example.praktam_2477051012.network

import com.example.praktam_2477051012.model.Tugas
import retrofit2.http.GET

interface ApiService {

    @GET("tugas.json")
    suspend fun getTugas(): List<Tugas>
}