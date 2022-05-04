package com.evg_ivanoff.thingstodo.model

import com.evg_ivanoff.thingstodo.controller.RetrofitClient
import com.evg_ivanoff.thingstodo.controller.RetrofitServices

object Common {
    private val BASE_URL = "https://www.boredapi.com/"
    val retrofitService: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}