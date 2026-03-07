package com.example.praktam_2477051012.model

import com.example.praktam_2477051012.R

object TugasSource {

    val tugasList = listOf(

        Tugas(
            judul = "Tugas Basis Data",
            matkul = "Basis Data",
            deskripsi = "Membuat ERD dan normalisasi database.",
            deadline = "20 Juni 2026",
            gambar = R.drawable.basis_data
        ),

        Tugas(
            judul = "Tugas Pemrograman Mobile",
            matkul = "Pemrograman Mobile",
            deskripsi = "Membuat aplikasi CRUD menggunakan SQLite.",
            deadline = "25 Juni 2026",
            gambar = R.drawable.pemrograman_mobile
        ),

        Tugas(
            judul = "Tugas Pemrograman Web",
            matkul = "Pemrograman Web",
            deskripsi = "Membuat website CRUD menggunakan PHP dan MySQL.",
            deadline = "30 Juni 2026",
            gambar = R.drawable.pemrograman_web
        )
    )
}