package com.example.newsapplication.models

import android.os.Parcelable
import com.example.newsapplication.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataForDetails(
    val title: String = "no data",
    val subsection: String = "no data",
    val abstract : String = "no data",
    val img_url: String = Constant.PLACEHOLDER_IMAGE
) : Parcelable
