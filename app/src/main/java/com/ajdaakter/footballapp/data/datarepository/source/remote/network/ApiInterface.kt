package com.ajdaakter.footballapp.data.datarepository.source.remote.network

import com.ajdaakter.footballapp.data.model.GetAllFixturesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(ApiConfig.GET_ALL_FIXTURES)
    fun getAllFixtures(): Call<GetAllFixturesResponse>
}