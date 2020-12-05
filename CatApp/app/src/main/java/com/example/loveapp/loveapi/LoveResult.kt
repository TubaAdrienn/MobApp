package com.example.loveapp.loveapi

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LoveResult (
    @field:Json(name = "fname")
    val fname: String,
    @field:Json(name = "sname")
    val sname: String,
    @field:Json(name = "percentage")
    val percentage: Int,
    @field:Json(name = "result")
    val result : String
    ) : Parcelable {}
