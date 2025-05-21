package com.pemrogamanmobile.myfavoritegames.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Games(
    @StringRes val detailResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val linkResourceId: Int,
    @StringRes val yearResourceId: Int,
    @StringRes val titleResourceId: Int,
)