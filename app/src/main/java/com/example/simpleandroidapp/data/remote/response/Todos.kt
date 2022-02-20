package com.example.simpleandroidapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todos(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
) : Parcelable
