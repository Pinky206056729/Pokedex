package com.siphokazi.pokedex.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.ripple.rememberRipple // Assuming you are using material ripple
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.siphokazi.pokedex.domain.model.PokemonListItem
import com.siphokazi.pokedex.presentation.viewmodel.HomeViewModel

// HOME SCREEN COMPOSABLE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onPokemonClick: (Int) -> Unit) {
    val state by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "PokéDex",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold) // Bold and slightly larger text
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Search Pokémon by Name or ID") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            when {
                state.isLoading -> CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(32.dp)
                )

                state.error != null -> Text(
                    "Error: ${state.error}",
                    modifier = Modifier.padding(16.dp)
                )

                else -> PokemonList(
                    list = state.filteredPokemonList,
                    onPokemonClick = onPokemonClick,
                    contentPadding = PaddingValues(top = 0.dp)
                )
            }
        }
    }
}

// POKEMON LIST
@Composable
fun PokemonList(
    list: List<PokemonListItem>,
    onPokemonClick: (Int) -> Unit,
    contentPadding: PaddingValues
) {
    LazyColumn(contentPadding = contentPadding) {
        items(list) { pokemon ->
            PokemonItemCard(pokemon = pokemon, onClick = { onPokemonClick(pokemon.id) })
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

// POKEMON ITEM CARD
@Composable
fun PokemonItemCard(pokemon: PokemonListItem, onClick: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val indication = rememberRipple(bounded = true)
    val cardBackgroundColor =
        getPokemonColor(pokemon.type)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 10.dp) // Generous padding
            .clip(RoundedCornerShape(12.dp)) // Rounded corners
            .clickable(
                interactionSource = interactionSource,
                indication = indication,
                onClick = onClick
            ),
        // Added elevation for visual separation
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBackgroundColor)
                .padding(horizontal = 4.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = "#${pokemon.id}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f), // Subtler color for ID
                    )
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            //Right Side: Arrow Icon
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Details",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )
        }
    }
}