package com.example.praktam_2477051012.model

import com.example.praktam_2477051012.R

object TugasSource {

    val tugasList = listOf(
        Tugas(
            judul = "Tugas Basis Data",
            matkul = "Basis Data",
            deskripsi = "Membuat ERD dan Normalisasi",
            deadline = "20 Juni 2026",
            gambar = R.drawable.kalender
        ),
        Tugas(
            judul = "Tugas Mobile",
            matkul = "Pemrograman Mobile",
            deskripsi = "Membuat CRUD SQLite",
            deadline = "25 Juni 2026",
            gambar = R.drawable.list
        ),
        Tugas(
            judul = "Tugas Web",
            matkul = "Pemrograman Web",
            deskripsi = "Membuat Website CRUD",
            deadline = "30 Juni 2026",
            gambar = R.drawable.silang
        )
    )
}