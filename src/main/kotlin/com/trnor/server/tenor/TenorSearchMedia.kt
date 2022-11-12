package com.trnor.server.tenor

/**
 * @author GrowlyX
 * @since 5/14/2022
 */
data class TenorSearchMedia(
    val tinymp4: TenorSearchMediaSpec,
    val gif: TenorSearchMediaSpec,
    val mp4: TenorSearchMediaSpec
)
