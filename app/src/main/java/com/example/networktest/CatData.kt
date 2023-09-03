package com.example.networktest
import com.google.gson.annotations.SerializedName

//TODO: CREIAMO UNA DATA CLASS (USANDO IL PLUGIN JSON TO KOTLIN DATA CLASS)
// AVANZATE - PROPERTIES, IMPOSTARE IL TIPO SU NULLABLE
// ANNOTATION - IMPOSTARE SU GSON


data class CatData(
    @SerializedName("fact")
    val fact: String?,
    @SerializedName("length")
    val length: Int?
)