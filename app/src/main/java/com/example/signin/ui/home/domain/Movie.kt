package com.example.signin.ui.home.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "movies")
@Parcelize
data class Movie(
    @PrimaryKey
    var id      : Int?,
    var movie   : String?,
    var rating  : Double?,
    var image   : String?,
    var imdb_url : String?
): Parcelable