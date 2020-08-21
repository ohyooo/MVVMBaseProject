package com.ohyooo.lib.extension

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String?) {
    text ?: return
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}