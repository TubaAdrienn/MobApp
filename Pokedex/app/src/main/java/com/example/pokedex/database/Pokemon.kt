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
    var desc: String,

    @ColumnInfo(name = "pokemon_type")
    var type: String,

    @ColumnInfo(name = "pokemon_ab")
    var ability: String,

    @ColumnInfo(name = "pokemon_attack")
    var attack: String,

    @ColumnInfo(name = "pokemon_def")
    var defense: String,

    @ColumnInfo(name = "pokemon_specAtt")
    var specAttack: String,

    @ColumnInfo(name = "pokemon_specDef")
    var specDef: String,

    @ColumnInfo(name = "pokemon_speed")
    var speed: String
)

