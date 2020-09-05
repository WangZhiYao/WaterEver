package me.zhiyao.waterever.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import coil.Coil
import coil.request.LoadRequest
import com.zhihu.matisse.engine.ImageEngine

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class CoilImageEngine : ImageEngine {
    override fun loadImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        if (context != null && imageView != null) {

            imageView.scaleType = ImageView.ScaleType.CENTER_CROP

            val imageLoader = Coil.imageLoader(context)

            val request = LoadRequest.Builder(context)
                .data(uri)
                .size(resizeX, resizeY)
                .target(imageView)
                .build()

            imageLoader.execute(request)
        }
    }

    override fun loadGifImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {

    }

    override fun supportAnimatedGif(): Boolean {
        return false
    }

    override fun loadGifThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {

    }

    override fun loadThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        if (context != null && imageView != null) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP

            val imageLoader = Coil.imageLoader(context)

            val request = LoadRequest.Builder(context)
                .data(uri)
                .size(resize)
                .placeholder(placeholder)
                .target(imageView)
                .build()

            imageLoader.execute(request)
        }
    }
}