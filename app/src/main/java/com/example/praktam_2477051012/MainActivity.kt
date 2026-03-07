package com.example.praktam_2477051012

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        TugasSource.tugasList.forEach { tugas ->

            DetailScreen(tugas)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailScreen(tugas: Tugas) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = tugas.gambar),
            contentDescription = tugas.judul,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = tugas.judul,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Mata Kuliah: ${tugas.matkul}"
        )

        Text(
            text = tugas.deskripsi
        )

        Text(
            text = "Deadline: ${tugas.deadline}",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tandai Selesai")
        }
    }
}