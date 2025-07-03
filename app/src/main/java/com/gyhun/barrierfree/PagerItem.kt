package com.gyhun.barrierfree

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagerItem(
    val imageUrl: String,
    val title: String,
    val address: String
) : Parcelable