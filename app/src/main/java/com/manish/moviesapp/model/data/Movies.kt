package com.manish.moviesapp.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Movies(

    @SerializedName("Search")
    @Expose
    val search: List<Movie>
)
