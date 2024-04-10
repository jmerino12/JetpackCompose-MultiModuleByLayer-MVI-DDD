package com.example.infrastructure.httpclient.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDto(
    @SerializedName("results") val results: List<MovieDto>,
) : Parcelable