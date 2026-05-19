package com.example.praktam_2477051012.data.repository

import com.example.praktam_2477051012.data.api.RetrofitClient
import com.example.praktam_2477051012.data.model.Tugas

class TugasRepository {

    suspend fun getTugas(): List<Tugas> {

        return try {

            RetrofitClient.instance.getTugas()

        } catch (e: Exception) {

            emptyList()
        }
    }
}