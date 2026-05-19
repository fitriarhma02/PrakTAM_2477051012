package com.example.praktam_2477051012.data.api

import com.example.praktam_2477051012.data.model.Tugas
import retrofit2.http.GET

interface ApiService {

    @GET("tugas.json")
    suspend fun getTugas(): List<Tugas>
}