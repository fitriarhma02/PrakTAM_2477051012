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
import androidx.compose.ui.unit.dp
import com.example.praktam_2477051012.model.Tugas
import com.example.praktam_2477051012.model.TugasSource
import com.example.praktam_2477051012.ui.theme.PraktamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PraktamTheme { // ✅ WAJIB modul 7
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
            .background(MaterialTheme.colorScheme.background) //  theme
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Rekomendasi Tugas",
                style = MaterialTheme.typography.titleLarge
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
                style = MaterialTheme.typography.titleLarge
            )
        }

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
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface //  theme
        )
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
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = tugas.matkul,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun DetailScreen(tugas: Tugas) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface //  theme
        )
    ) {

        Column {

            Box {

                Image(
                    painter = painterResource(id = tugas.gambar),
                    contentDescription = tugas.judul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(50)
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite)
                            MaterialTheme.colorScheme.primary //  theme
                        else
                            Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = tugas.judul,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Mata Kuliah: ${tugas.matkul}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = tugas.deskripsi,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Deadline: ${tugas.deadline}",
                    style = MaterialTheme.typography.bodyMedium
                )

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