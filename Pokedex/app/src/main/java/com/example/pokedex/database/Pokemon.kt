package com.example.pokedex.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var pokeId: Long = 0L,

    @ColumnInfo(name = "pokemon_name")
    val name: String,

    @ColumnInfo(name = "pokemon_desc")
    var desc: String
)

