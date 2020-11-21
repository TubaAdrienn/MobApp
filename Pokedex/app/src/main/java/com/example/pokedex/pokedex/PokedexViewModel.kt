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
    val navigateToSleepDetail: LiveData<Long>
        get() = _navigateToPokemonDetail

    fun onPokemonClicked(id: Long) {
        _navigateToPokemonDetail.value = id
    }

    fun onPokemonDetailNavigated() {
        _navigateToPokemonDetail.value = null
    }

    /**
     * Hold a reference to PokemonDatabase via PokemonDatabaseDao.
     */
    val database = dataSource

    val charm = Pokemon(1, "Charmander", "It's a Chamander.")
    val chari = Pokemon(2, "Charizard", "It's a Charizard.")
    val mewtwo = Pokemon(3, "Mewtwo", "It's a Mewtwo.")
    val pikachu = Pokemon(4, "Pikachu", "It's a Pikcahu.")

    private suspend fun insert(poke: Pokemon) {
        database.insert(poke)
    }
}