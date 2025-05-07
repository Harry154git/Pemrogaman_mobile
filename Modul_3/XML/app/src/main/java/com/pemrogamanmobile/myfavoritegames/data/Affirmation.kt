package com.pemrogamanmobile.myfavoritegames.data

import java.io.Serializable

data class Affirmation(
    val detailResourceId: Int,
    val imageResourceId: Int,
    val steamLinkResourceId: Int,
    val yearResourceId: Int,
    val titleResourceId: Int
) : Serializable