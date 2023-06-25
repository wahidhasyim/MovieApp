package com.wahidhasyim.movieapp.util

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.wahidhasyim.movieapp.R

inline val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
inline val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
inline val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)

fun ImageView.loadImage(
    url: String?,
    roundCorner: Int = 0,
    makeItCircle: Boolean = false,
    placeholder: Int? = null,
    skipMemory: Boolean = false,
    errorDrawable: Int? = null,
    onError: (() -> Unit)? = null,
    onLoadDone: (() -> Unit)? = null,
) {
    val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    val random: () -> String = {
        (1..10)
            .map { chars.random() }
            .joinToString("")
    }

    val builder = Glide.with(this)
        .load(if (skipMemory) "$url?key=$random" else url)
        .diskCacheStrategy(if (skipMemory) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)

    if (skipMemory) {
        builder.signature(ObjectKey(System.currentTimeMillis()))
    }

    if (makeItCircle) builder.circleCrop()
    else if (roundCorner > 0) builder.transform(CenterCrop(), RoundedCorners(roundCorner.dp))

    builder.placeholder(
        placeholder ?: android.R.drawable.progress_indeterminate_horizontal
    ) // need placeholder to avoid issue like glide annotations
        .error(errorDrawable ?: android.R.drawable.stat_notify_error)
        .skipMemoryCache(skipMemory).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                onLoadDone?.invoke()
                onError?.invoke()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                onLoadDone?.invoke()
                return false
            }
        }).into(this)
}

