package com.siphokazi.pokedex.presentation.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.siphokazi.pokedex.presentation.viewmodel.DetailViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.model.PokemonStat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    pokemonId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {

    LaunchedEffect(pokemonId) {
        viewModel.loadPokemonDetail(pokemonId)
    }

    val state by viewModel.uiState.collectAsState()

    // Determine the theme color based on the primary type or a fallback
    val primaryColor = state.pokemon?.types?.firstOrNull()?.let {
        getPokemonColor(it)
    } ?: MaterialTheme.colorScheme.primaryContainer

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        state.pokemon?.name ?: "Loading...",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor
                )
            )
        }
    ) { padding ->

        when {
            state.isLoading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(Modifier.size(64.dp)) }

            state.error != null -> Text(
                "Error loading Pokémon: ${state.error}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(padding).padding(16.dp)
            )

            state.pokemon != null -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        DetailHeader(pokemon = state.pokemon!!, primaryColor = primaryColor)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {
                        // Metrics Card (Height and Weight)
                        MetricCard(pokemon = state.pokemon!!)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {
                        // Types and Abilities
                        TypeAbilityChips(pokemon = state.pokemon!!)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {
                        // Stats Section
                        Text(
                            text = "Base Stats",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    items(state.pokemon!!.stats) { stat ->
                        StatProgressBar(stat = stat, color = primaryColor)
                    }

                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }
            }
        }
    }
}

// Detail Header (Image, Name, ID)
@Composable
fun DetailHeader(pokemon: PokemonDetail, primaryColor: Color) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(primaryColor.copy(alpha = 0.4f)) // Use lighter version of primary color
            .padding(16.dp)
    ) {
        // Pokémon Name and ID
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "#${pokemon.id}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Pokémon Image with a circular background
        AsyncImage(
            model = pokemon.frontSpriteUrl,
            contentDescription = "${pokemon.name} front sprite",
            modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.7f)) // White background for visibility
                .padding(4.dp)
        )
    }
}

// Metrics Card (Height and Weight)
@Composable
fun MetricCard(pokemon: PokemonDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MetricItem(
                label = "Height",
                value = String.format("%.2f m", pokemon.heightInMeters)
            )
            Divider(
                modifier = Modifier
                    .height(48.dp)
                    .width(1.dp)
            )
            MetricItem(
                label = "Weight",
                value = String.format("%.2f kg", pokemon.weightInKg)
            )
        }
    }
}

@Composable
fun MetricItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

// Types and Abilities Chips
@Composable
fun TypeAbilityChips(pokemon: PokemonDetail) {
    Column(modifier = Modifier.fillMaxWidth()) {

        // Types
        Text("Type(s):", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            pokemon.types.forEach { type ->
                // Chip design for types
                TypeChip(name = type, color = getPokemonColor(type))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Abilities
        Text("Abilities:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            pokemon.abilities.forEach { ability ->
                // Chip design for abilities (using a secondary color)
                AbilityChip(name = ability)
            }
        }
    }
}

@Composable
fun TypeChip(name: String, color: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun AbilityChip(name: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

// Stat Progress Bar
@Composable
fun StatProgressBar(stat: PokemonStat, color: Color) {

    val maxStatValue = 180f
    val targetProgress = stat.value / maxStatValue

    // Animation for a smoother look when loading
    val progress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 1000),
        label = "Stat Progress"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Stat Name
        Text(
            text = "${stat.name}:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.width(100.dp) // Fixed width for alignment
        )

        Spacer(Modifier.width(8.dp))

        // Value and Progress Bar
        Box(
            modifier = Modifier
                .weight(1f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceAtMost(1f))
                    .background(color)
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = stat.value.toString(),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = if (progress > 0.4f) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

