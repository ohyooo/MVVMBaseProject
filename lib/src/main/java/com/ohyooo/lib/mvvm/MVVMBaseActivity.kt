package com.ohyooo.lib.mvvm

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class MVVMBaseActivity(@LayoutRes val layoutId: Int = 0) : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != 0) {
            DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        }
    }

}