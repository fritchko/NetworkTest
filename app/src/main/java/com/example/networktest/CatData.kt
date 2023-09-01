package com.example.networktest
import com.google.gson.annotations.SerializedName


data class CatData(
    @SerializedName("fact")
    val fact: String?,
    @SerializedName("length")
    val length: Int?
)