package org.example.trnor.tenor

/**
 * @author GrowlyX
 * @since 5/14/2022
 */
data class TenorSearchResult(
    val id: String,
    val title: String,
    val media: List<TenorSearchMedia>
)
