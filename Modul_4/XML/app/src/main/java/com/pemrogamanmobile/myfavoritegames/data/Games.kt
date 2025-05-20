package com.pemrogamanmobile.myfavoritegames.data

import java.io.Serializable

data class Games(
    val detailResourceId: Int,
    val imageResourceId: Int,
    val steamLinkResourceId: Int,
    val yearResourceId: Int,
    val titleResourceId: Int,
    val category: String
) : Serializable