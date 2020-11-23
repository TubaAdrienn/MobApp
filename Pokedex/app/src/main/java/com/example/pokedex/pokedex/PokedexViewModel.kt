package com.example.pokedex.pokedex

import android.app.Application
import androidx.lifecycle.*
import com.example.pokedex.database.Pokemon
import com.example.pokedex.database.PokemonDatabaseDao

class PokedexViewModel(
    dataSource: PokemonDatabaseDao,
    application: Application
) : ViewModel() {

    private val _navigateToPokemonDetail = MutableLiveData<Long>()
    val navigateToDetail: LiveData<Long>
        get() = _navigateToPokemonDetail

    fun onPokemonClicked(poke: Long) {
        _navigateToPokemonDetail.value = poke
    }

    fun onPokemonDetailNavigated() {
        _navigateToPokemonDetail.value = null
    }

    /**
     * Hold a reference to PokemonDatabase via PokemonDatabaseDao.
     */
    val database = dataSource

    val charm = Pokemon(
        1, "Charmander", "It's a Chamander.",
        "fire","fast attack", "fast attack",
        "def", "specAt", "specDef", "much speed"
    )
    val chari = Pokemon(
        2, "Charizard", "It's a Charizard.",
        "fire","fast attack", "fast attack",
        "def", "specAt", "specDef", "much speed"
    )
    val mewtwo = Pokemon(
        3,
        "Mewtwo",
        "It's a Mewtwo.",
        "fire","fast attack", "fast attack",
        "def", "specAt", "specDef", "much speed"
    )
    val pikachu = Pokemon(
        4,
        "Pikachu",
        "It's a Pikcahu.",
        "fire","fast attack", "fast attack",
        "def", "specAt", "specDef", "much speed"
    )

    private suspend fun insert(poke: Pokemon) {
        database.insert(poke)
    }
}