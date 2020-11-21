package com.example.pokedex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDatabaseDao {

    @Insert
    suspend fun insert(night: Pokemon)

    @Update
    suspend fun update(night: Pokemon)

    @Query("DELETE FROM pokemon_table")
    suspend fun clear()

    @Query("SELECT * FROM pokemon_table ORDER BY pokemon_name DESC")
    fun getAllPokemons(): LiveData<List<Pokemon>>

    @Query("SELECT * from pokemon_table WHERE pokeId = :key")
    fun getPokemonWithId(key: Long): LiveData<Pokemon>
}
