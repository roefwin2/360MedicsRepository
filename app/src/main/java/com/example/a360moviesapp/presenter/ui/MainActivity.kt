package com.example.a360moviesapp.presenter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.a360moviesapp.data.models.NetworkMovie
import com.example.a360moviesapp.ui.theme.MoviesAppTheme
import com.example.a360moviesapp.utils.Error
import com.example.a360moviesapp.utils.Loading
import com.example.a360moviesapp.utils.NotStarted
import com.example.a360moviesapp.utils.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state = viewModel.state.value.fetchData

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SearchBar {
                                viewModel.changeTitle(it)
                                if (it.isEmpty()) {
                                    viewModel.resetState()
                                }
                            }
                            when (state) {
                                is Loading<NetworkMovie> -> CircularProgressIndicator()
                                is Error ->
                                    Snackbar() {
                                        Text(text = state.msg)
                                    }
                                is Success -> {
                                    MovieItem(movie = state.data)
                                }
                                is NotStarted -> {
                                }
                            }

                            OutlinedButton(
                                enabled = viewModel.state.value.isEnable,
                                onClick = { viewModel.fetchMovie() }) {
                                Text(text = "Valid")
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(onValueChanged: ((String) -> Unit)) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

    }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Enter Your Title") },
        onValueChange = {
            text = it
            onValueChanged.invoke(text)
        })
}

@Composable
fun MovieItem(movie: NetworkMovie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row() {
            Image(
                painter = rememberAsyncImagePainter(movie.poster),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Column() {
                Text(text = movie.title)
                Text(text = movie.plot)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesAppTheme {
        val networkMovie =
            NetworkMovie(
                "", "",
                "https://m.media-amazon.com/images/M/MV5BYmZkYWRlNWQtOGY0Zi00MWZkLWJiZTktNjRjMDY4MTU2YzAyXkEyXkFqcGdeQXVyMzYzNzc1NjY@._V1_SX300.jpg",
                "", emptyList(),
                "top gun", "", "lourd", ""
            )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar {
                }
                MovieItem(movie = networkMovie)
            }
            OutlinedButton(onClick = { }) {
                Text(text = "Valid")
            }
        }
    }
}