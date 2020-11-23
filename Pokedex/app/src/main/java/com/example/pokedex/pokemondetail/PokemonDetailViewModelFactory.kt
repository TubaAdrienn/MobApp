package com.example.pokedex.pokemondetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.database.Pokemon
import com.example.pokedex.database.PokemonDatabaseDao
import com.example.pokedex.pokedex.PokedexViewModel


class PokemonDetailViewModelFactory(
    private val pokeKey: Long,
    private val dataSource: PokemonDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(pokeKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}