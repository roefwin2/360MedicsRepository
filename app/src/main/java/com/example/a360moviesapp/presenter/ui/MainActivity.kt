package com.example.a360moviesapp.presenter.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.a360moviesapp.data.models.NetworkMovie
import com.example.a360moviesapp.ui.theme.MoviesAppTheme
import com.example.a360moviesapp.utils.Error
import com.example.a360moviesapp.utils.Loading
import com.example.a360moviesapp.utils.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   val state  = viewModel.state.value.fetchData
                    Column {
                        SearchBar {
                            viewModel.changeTitle(it)
                        }
                        when(state){
                           is Loading<NetworkMovie> -> CircularProgressIndicator()
                            is Error -> Toast.makeText(applicationContext,state.msg,Toast.LENGTH_LONG).show()
                            is Success -> {
                                MovieItem(movie = state.data)
                            }
                        }

                        OutlinedButton(onClick = { viewModel.fetchMovie()}) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(onValueChanged : ((String) -> Unit)) {
    OutlinedTextField(value = "" , onValueChange = {
        onValueChanged.invoke(it)
    } )
}

@Composable
fun MovieItem(movie: NetworkMovie) {
    Card() {
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
            NetworkMovie("", "",
                "https://m.media-amazon.com/images/M/MV5BYmZkYWRlNWQtOGY0Zi00MWZkLWJiZTktNjRjMDY4MTU2YzAyXkEyXkFqcGdeQXVyMzYzNzc1NjY@._V1_SX300.jpg",
                "", emptyList(),
                "top gun", "", "lourd", "")
        MovieItem(movie = networkMovie)
    }
}