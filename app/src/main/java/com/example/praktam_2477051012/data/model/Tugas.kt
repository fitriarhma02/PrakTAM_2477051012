package com.example.praktam_2477051012.data.model

import com.google.gson.annotations.SerializedName

data class Tugas(

    val judul: String = "",
    val matkul: String = "",
    val deskripsi: String = "",
    val deadline: String = "",

    @SerializedName("image_url")
    val imageUrl: String = "",

    var isFavorite: Boolean = false
)