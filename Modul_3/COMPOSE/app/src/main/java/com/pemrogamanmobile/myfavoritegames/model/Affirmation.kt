package com.pemrogamanmobile.myfavoritegames.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val detailResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val steamLinkResourceId: Int,
    @StringRes val yearResourceId: Int,
    @StringRes val titleResourceId: Int,
)