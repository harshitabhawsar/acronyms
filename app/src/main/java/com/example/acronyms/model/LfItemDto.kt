package com.example.acronyms.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LfItemDto(
    val freq: Int,
    val lf: String,
    val since: Int
):Parcelable