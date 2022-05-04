package com.evg_ivanoff.thingstodo.controller

import com.evg_ivanoff.thingstodo.model.Action
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("api/activity")
    fun getRandomActivity(): Call<Action>

    @GET("api/activity")
    fun getActivityByType(@Query("type") type: List<String>): Call<Action>

}