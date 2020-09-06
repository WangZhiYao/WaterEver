package me.zhiyao.waterever.config

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.zhihu.matisse.engine.ImageEngine

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class GlideEngine : ImageEngine {
    override fun loadImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {

        if (context != null && imageView != null) {
            GlideApp.with(context)
                .load(uri)
                .override(resizeX, resizeY)
                .into(imageView)
        }
    }

    override fun loadGifImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        if (context != null && imageView != null) {
            GlideApp.with(context)
                .load(uri)
                .override(resizeX, resizeY)
                .into(imageView)
        }
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
        if (context != null && imageView != null) {
            GlideApp.with(context)
                .load(uri)
                .override(resize)
                .placeholder(placeholder)
                .into(imageView)
        }
    }

    override fun loadThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        if (context != null && imageView != null) {
            GlideApp.with(context)
                .load(uri)
                .override(resize)
                .placeholder(placeholder)
                .into(imageView)
        }
    }
}