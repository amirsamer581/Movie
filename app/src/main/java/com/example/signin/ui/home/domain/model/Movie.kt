package com.example.signin.ui.home.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    var id      : Int?,

    @SerializedName("movie")
    var movie   : String?,

    @SerializedName("rating")
    var rating  : Double?,

    @SerializedName("image")
    var image   : String?,

    @SerializedName("imdb_url")
    var imdb_url : String?

): Parcelable