package com.manish.moviesapp.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(

    @SerializedName("Title")
    @Expose
    val title: String,

    @SerializedName("Year")
    @Expose
    val year: String,

    @SerializedName("Type")
    @Expose
    val type: String,

    @SerializedName("imdbID")
    @Expose
    val imdbID: String,


    @SerializedName("Poster")
    @Expose
    val poster: String,

    )
