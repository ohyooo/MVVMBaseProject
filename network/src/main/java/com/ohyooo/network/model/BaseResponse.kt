package com.ohyooo.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseResponse(var errorCode: Int = 0, var errorMsg: String? = null) : Parcelable