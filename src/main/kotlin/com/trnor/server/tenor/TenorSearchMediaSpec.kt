package com.trnor.server.tenor

import java.util.StringJoiner

/**
 * @author GrowlyX
 * @since 5/14/2022
 */
data class TenorSearchMediaSpec(
    val preview: String,
    val size: Int,
    val duration: String,
    val dims: List<Int>,
    val url: String
)
