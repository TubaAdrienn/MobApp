package com.example.loveapp.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "love_table")
data class LoveResultSave(
    @PrimaryKey(autoGenerate = true)
    val id : Long=0,

    @ColumnInfo(name = "sname")
    var sname: String ="",

    @ColumnInfo(name = "fname")
    var fname: String ="",

    @ColumnInfo(name = "percentage")
    var percentage: Int =0,

    @ColumnInfo(name = "result")
    var result : String = "" )

