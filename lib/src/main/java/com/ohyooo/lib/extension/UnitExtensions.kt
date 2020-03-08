package com.ohyooo.lib.extension

import android.content.Context
import android.util.TypedValue

fun Context.dp2px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.sp2px(sp: Float) = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics))

fun Context.pt2px(pt: Float) = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, pt, resources.displayMetrics))

fun Context.in2px(inch: Float) = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, inch, resources.displayMetrics))

fun Context.mm2px(mm: Float) = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, resources.displayMetrics))



