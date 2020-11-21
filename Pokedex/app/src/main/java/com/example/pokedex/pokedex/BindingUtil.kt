package com.example.pokedex.pokedex

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.pokedex.R
import com.example.pokedex.database.Pokemon

@BindingAdapter("pokemonImage")
fun ImageView.setPokemonImage(item: Pokemon?) {
    item?.let {
        setImageResource(
            when (item.name) {
                "Charizard" -> R.drawable.chari
                "Charmander" -> R.drawable.charm
                "Mewtwo" -> R.drawable.mewtwo
                "Pikachu" -> R.drawable.pik
                else -> R.drawable.pok
            }
        )
    }
}

@BindingAdapter("pokemonName")
fun TextView.setPokemonName(item: Pokemon?) {
    item?.let {
        text = item.name
    }
}
