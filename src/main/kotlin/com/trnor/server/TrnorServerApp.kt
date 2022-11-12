package com.trnor.server

import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import io.javalin.Javalin
import com.trnor.server.tenor.TenorSearchIdExtractor
import com.trnor.server.tenor.TenorSearchResponse
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URL
import javax.imageio.ImageIO


/**
 * @author GrowlyX
 * @since 5/11/2022
 */
object TrnorServerApp
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val app = Javalin
            .create()
            .start(5000)

        val gson = GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .serializeNulls()
            .create()

        val cache =
            mutableMapOf<Long, TenorSearchResponse>()

        val okIPullUp =
            File("ok-i-pull-up.gif")
                .inputStream()

        val fgImage = ImageIO.read(okIPullUp)
        val width = fgImage.getWidth(null)
        val height = fgImage.getHeight(null)

        app.get("/*") {
            val path = it.req().pathInfo

            if (path.contains(".ico"))
            {
                return@get
            }

            val id = TenorSearchIdExtractor
                .extractId(path)
                ?.toLongOrNull()
                ?: return@get

            val deserialized = if (cache[id] != null)
            {
                cache[id]
            } else
            {
                val endpoint =
                    template.format(id)

                val url = URL(endpoint)
                val json = url.readText()

                gson
                    .fromJson(
                        json, TenorSearchResponse::class.java
                    )
            } ?: return@get

            if (deserialized.results.isEmpty())
            {
                return@get
            }

            val result = deserialized
                .results.first()

            if (result.media.isEmpty())
            {
                return@get
            }

            val media = result.media.first()
            val gif = URL(media.gif.url)

            // Create a non-transparent BG image
            val bgImage = ImageIO.read(gif)

            // Create the final image
            val finalImage = BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB
            )

            val scaled = resize(bgImage)
            val graphics = finalImage.createGraphics()

            graphics.drawImage(scaled, 0, 78, null)
            graphics.drawImage(fgImage, 0, 0, null)
            graphics.dispose()

            val output = ByteArrayOutputStream()
            ImageIO.write(finalImage, "png", output)

            val inputStream =
                ByteArrayInputStream(
                    output.toByteArray()
                )

            it.res().contentType = "image/png"
            it.result(inputStream)
        }
    }

    private fun resize(
        image: BufferedImage
    ): BufferedImage
    {
        val tmp = image
            .getScaledInstance(
                400, 300, Image.SCALE_SMOOTH
            )

        val bufferedImage =
            BufferedImage(
                400, 300, BufferedImage.TYPE_INT_ARGB
            )

        val graphics =
            bufferedImage.createGraphics()

        graphics.drawImage(tmp, 0, 0, null)
        graphics.dispose()

        return bufferedImage
    }
}
