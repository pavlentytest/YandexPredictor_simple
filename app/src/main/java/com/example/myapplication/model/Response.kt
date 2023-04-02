package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("endOfWord")
    @Expose
    var endOfWord: Boolean? = null

    @SerializedName("pos")
    @Expose
    var pos: Int? = null

    @SerializedName("text")
    @Expose
    var text: List<String>? = null
}