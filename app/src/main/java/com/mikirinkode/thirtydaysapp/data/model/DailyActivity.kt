package com.mikirinkode.thirtydaysapp.data.model

import androidx.annotation.DrawableRes

data class DailyActivity(
    val name: String,
    val description: String,
    @DrawableRes val illustrationId: Int,
)
