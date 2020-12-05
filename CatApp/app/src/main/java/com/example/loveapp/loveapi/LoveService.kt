package com.example.loveapp.loveapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://love-calculator.p.rapidapi.com/"
private const val API_KEY = "x-rapidapi-key: 1434b5828dmsh57c108713d5e9efp191635jsnc07f3f8ab810"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LoveService {

    @Headers("x-rapidapi-key:1434b5828dmsh57c108713d5e9efp191635jsnc07f3f8ab810")
    @GET("getPercentage")
    suspend fun getLoveResult(@Query("fname") fname:String,@Query("sname") sname:String ) : LoveResult
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object LoveApi {
    val loveService: LoveService by lazy { retrofit.create(LoveService::class.java) }
}
