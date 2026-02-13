package no.uio.ifi.in2000.viljardh.vitseapplive.ui.jokes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes.Joke

@Composable
fun JokesScreen (jokesViewModel: JokesViewModel = viewModel()) {
    val jokesUiState by jokesViewModel.jokesUiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Haha Jokes", style = MaterialTheme.typography.titleLarge)
        LazyColumn (
            modifier = Modifier.padding(10.dp)
        ) {
            items(jokesUiState.jokes) {joke ->
                JokeCard(joke)
            }
        }
    }
}

@Composable
fun JokeCard (
    joke: Joke
) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.elevatedCardColors(Color.DarkGray),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Category: ${joke.category}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = joke.joke,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}