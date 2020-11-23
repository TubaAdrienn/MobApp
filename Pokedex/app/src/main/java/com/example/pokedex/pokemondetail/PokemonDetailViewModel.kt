package com.example.pokedex.pokemondetail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.Pokemon
import com.example.pokedex.database.PokemonDatabaseDao
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokeKey:Long,
    dataSource: PokemonDatabaseDao) : ViewModel() {

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

    val pokemons = arrayListOf<Pokemon>(
        mewtwo,
        pikachu,
        charm,
        chari
    )

    val database = dataSource

    private val poke: Pokemon

    fun getPokemon() = poke

    init {
        poke=pokemons.find{ it.pokeId ==pokeKey } as Pokemon
    }

    private val _navigateToPokedex = MutableLiveData<Boolean?>()

    public suspend fun insert(poke: Pokemon) {
        database.insert(poke)
    }

    fun onClickLike() {
        viewModelScope.launch {
            val pokemon = pokemons.find { it.pokeId ==pokeKey }
            if(database.getPokemonWithId(pokeKey)!=null) {
                if (pokemon != null) {
                    insert(pokemon)
                    print("Pokemon saved.")
                }
            }
        }
    }

}