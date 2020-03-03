package com.ohyooo.demo.model

import android.os.Parcelable
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainUIItem(val coreRemaining: ObservableField<String> = ObservableField("0"),
                 val searchRemaining: ObservableField<String> = ObservableField("0")
) : Parcelable