package com.example.praktam_2477051012

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.praktam_2477051012.data.model.Tugas
import com.example.praktam_2477051012.data.repository.TugasRepository
import com.example.praktam_2477051012.ui.theme.PraktamTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PraktamTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    var selectedTugas by remember {
        mutableStateOf<Tugas?>(null)
    }

    if (selectedTugas == null) {

        DaftarTugasScreen(
            onClick = {
                selectedTugas = it
            }
        )

    } else {

        DetailScreen(
            tugas = selectedTugas!!,
            onBack = {
                selectedTugas = null
            }
        )
    }
}

@Composable
fun DaftarTugasScreen(onClick: (Tugas) -> Unit) {

    var tugasList by remember {
        mutableStateOf<List<Tugas>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    val repository = remember {
        TugasRepository()
    }

    LaunchedEffect(Unit) {

        try {

            tugasList =
                repository.getTugas()
                    .sortedByDescending {
                        it.isFavorite
                    }

            isLoading = false
            isError = false

        } catch (e: Exception) {

            isLoading = false
            isError = true
        }
    }

    if (isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }

    } else if (isError || tugasList.isEmpty()) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),

            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Gagal Memuat Data",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Red
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Periksa koneksi internet anda"
                )
            }
        }

    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .statusBarsPadding(),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            item {

                Text(
                    text = "Daftar Tugas",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                LazyRow(
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    items(tugasList) { tugas ->

                        TugasRowItem(
                            tugas = tugas,
                            onClick = onClick,
                            onFavoriteClick = {

                                tugas.isFavorite =
                                    !tugas.isFavorite

                                tugasList =
                                    tugasList.sortedByDescending {
                                        it.isFavorite
                                    }
                            }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Daftar Mata Kuliah",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(tugasList) { tugas ->

                TugasListItem(
                    tugas = tugas,
                    onClick = onClick,
                    onFavoriteClick = {

                        tugas.isFavorite =
                            !tugas.isFavorite

                        tugasList =
                            tugasList.sortedByDescending {
                                it.isFavorite
                            }
                    }
                )
            }
        }
    }
}

@Composable
fun TugasRowItem(
    tugas: Tugas,
    onClick: (Tugas) -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable {
                onClick(tugas)
            },

        shape = RoundedCornerShape(12.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column {

            Box {

                AsyncImage(
                    model = tugas.imageUrl,
                    contentDescription = tugas.judul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {

                    Icon(
                        imageVector =
                            if (tugas.isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,

                        contentDescription = "Favorite",

                        tint =
                            if (tugas.isFavorite)
                                Color.Red
                            else
                                Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

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
fun TugasListItem(
    tugas: Tugas,
    onClick: (Tugas) -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(tugas)
            },

        shape = RoundedCornerShape(16.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column {

            Box {

                AsyncImage(
                    model = tugas.imageUrl,
                    contentDescription = tugas.judul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {

                    Icon(
                        imageVector =
                            if (tugas.isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,

                        contentDescription = "Favorite",

                        tint =
                            if (tugas.isFavorite)
                                Color.Red
                            else
                                Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = tugas.judul,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = tugas.matkul,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun DetailScreen(
    tugas: Tugas,
    onBack: () -> Unit
) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    val coroutineScope =
        rememberCoroutineScope()

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {

            Box {

                AsyncImage(
                    model = tugas.imageUrl,

                    contentDescription = tugas.judul,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),

                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = {
                        tugas.isFavorite =
                            !tugas.isFavorite
                    },

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {

                    Icon(
                        imageVector =
                            if (tugas.isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,

                        contentDescription = "Favorite",

                        tint =
                            if (tugas.isFavorite)
                                Color.Red
                            else
                                Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

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

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {

                        coroutineScope.launch {

                            isLoading = true

                            delay(2000)

                            snackbarHostState.showSnackbar(
                                "Tugas ${tugas.judul} selesai!"
                            )

                            isLoading = false
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),

                    enabled = !isLoading
                ) {

                    if (isLoading) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),

                            color = MaterialTheme
                                .colorScheme
                                .onPrimary,

                            strokeWidth = 2.dp
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text("Memproses...")

                    } else {

                        Text("Tandai Selesai")
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = onBack,

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme.colorScheme.secondary
                    )
                ) {

                    Text("Kembali")
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,

            modifier = Modifier.align(
                Alignment.BottomCenter
            )
        )
    }
}