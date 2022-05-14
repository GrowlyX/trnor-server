package org.example.trnor.tenor

/**
 * @author GrowlyX
 * @since 5/14/2022
 */
object TenorSearchIdExtractor
{
    fun extractId(endpoint: String): String?
    {
        return endpoint
            .split("-")
            .lastOrNull()
    }
}
