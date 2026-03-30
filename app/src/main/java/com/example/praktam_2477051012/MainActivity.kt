package com.example.praktam_2477051012

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.praktam_2477051012.model.Tugas
import com.example.praktam_2477051012.model.TugasSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DaftarTugasScreen()
            }
        }
    }
}

@Composable
fun DaftarTugasScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 🔥 HEADER
        item {
            Text(
                text = "Rekomendasi Tugas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(TugasSource.tugasList) { tugas ->
                    TugasRowItem(tugas)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Daftar Tugas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // 🔥 LIST UTAMA
        items(TugasSource.tugasList) { tugas ->
            DetailScreen(tugas)
        }
    }
}

@Composable
fun TugasRowItem(tugas: Tugas) {

    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {

            Image(
                painter = painterResource(id = tugas.gambar),
                contentDescription = tugas.judul,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = tugas.judul,
                    fontWeight = FontWeight.Bold
                )
                Text(text = tugas.matkul)
            }
        }
    }
}

@Composable
fun DetailScreen(tugas: Tugas) {

    // 🔥 STATE (MODUL 5)
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column {

            // 🔥 BOX (biar icon di atas gambar)
            Box {

                Image(
                    painter = painterResource(id = tugas.gambar),
                    contentDescription = tugas.judul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                // 🔥 ICON LOVE (SUDAH PASTI KELIHATAN)
                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(50))
                ) {
                    Icon(
                        imageVector = if (isFavorite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = tugas.judul,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(text = "Mata Kuliah: ${tugas.matkul}")
                Text(text = tugas.deskripsi)
                Text(text = "Deadline: ${tugas.deadline}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tandai Selesai")
                }
            }
        }
    }
}