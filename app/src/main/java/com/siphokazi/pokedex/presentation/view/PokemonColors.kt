package com.siphokazi.pokedex.presentation.view

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme

@Composable
fun getPokemonColor(type: String): Color {

    return when (type.lowercase()) {
        "normal" -> Color(0xFFF0F0F0)
        "fire" -> Color(0xFFFFE0B2) // Light Orange/Peach
        "water" -> Color(0xFFB3E5FC) // Light Blue
        "grass" -> Color(0xFFC8E6C9) // Light Green
        "bug" -> Color(0xFFF0F4C3)
        "electric" -> Color(0xFFFFECB3) // Light Yellow
        "poison" -> Color(0xFFE1BEE7) // Light Purple
        "ground" -> Color(0xFFD7CCC8)
        "fairy" -> Color(0xFFF8BBD0)
        "fighting" -> Color(0xFFFFCDD2)
        "psychic" -> Color(0xFFE6C9F0)
        "rock" -> Color(0xFFE0E0E0)
        "ghost" -> Color(0xFFC5CAE9)
        "ice" -> Color(0xFFB2EBF2)
        "dragon" -> Color(0xFFB39DDB)
        "steel" -> Color(0xFFCFD8DC)
        else -> MaterialTheme.colorScheme.surfaceVariant // Fallback color
    }
}