package com.movie.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieScreen(
    state: MoviesContract.State,
    onEventSent: (event: MoviesContract.Event) -> Unit,
    effectFlow: Flow<MoviesContract.Effect>?,
    onNavigationRequested: (MoviesContract.Effect.Navigation) -> Unit
) {
    Scaffold {
        when {
            state.isLoading -> Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }

            state.movies.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.padding(it)) {
                    items(state.movies) {
                        Text(text = it.title)
                    }
                }
            }
        }

    }
}