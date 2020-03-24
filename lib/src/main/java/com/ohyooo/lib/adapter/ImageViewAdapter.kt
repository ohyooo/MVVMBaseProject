package com.ohyooo.lib.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageViewAdapter {

    @JvmStatic
    @BindingAdapter("bitmap")
    fun setBitmap(iv: ImageView, bitmap: Bitmap?) {
        iv.setImageBitmap(bitmap)
    }
}