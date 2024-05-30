package ke.co.banit.composenavigation.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author: Angatia Benson
 * @Date: 5/30/2024
 * Copyright (c) 2024 BanIT
 */
data class Spacing (
    val default: Dp = 0.dp,
    val extraSmall: Dp = 5.dp,
    val small: Dp = 10.dp,
    val medium: Dp = 15.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 25.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }