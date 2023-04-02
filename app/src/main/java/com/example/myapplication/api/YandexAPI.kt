package com.example.myapplication.api

import com.example.myapplication.model.Response
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query


interface YandexAPI {
    // https://predictor.yandex.net?
    // key=pdct.1.1.20220412T145449Z.ed53b660ddacdc8e.353ee4c0c5adc174b6be636450d97faa6e34a072
    // &q=hello+wo
    // &lang=en
    // &limit=5
    @GET("/api/v1/predict.json/complete")
    fun getComplete(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("lang") lang: String,
        @Query("limit") limit: Int?
    ): Call<Response>
}