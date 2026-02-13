package no.uio.ifi.in2000.viljardh.vitseapplive.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

import no.uio.ifi.in2000.viljardh.vitseapplive.model.alpacas.PartyInfo

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = viewModel()) {
    val alpacaPartiesUiState by homeScreenViewModel.alpacaPartiesUiState.collectAsState()

    Column (modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = "Partier", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(alpacaPartiesUiState.parties) { partyInfo ->
                Log.d("Homescreen", "calling ${partyInfo.name}")
                PartyCard(
                    party = partyInfo
                )
            }
        }
    }

}

@Composable
fun PartyCard(
    party: PartyInfo,
    cardColor: Color = Color(party.color.toColorInt())
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = party.img,
                contentDescription = party.leader,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = party.name,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Leader: ${party.leader}",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
        }
    }
}