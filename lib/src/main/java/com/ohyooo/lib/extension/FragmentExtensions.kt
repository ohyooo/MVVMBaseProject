package com.ohyooo.lib.extension

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ohyooo.lib.mvvm.MVVMBaseFragment
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import com.ohyooo.lib.mvvm.MVVMViewModelFactory

inline fun <reified VB : ViewDataBinding> Fragment.viewDataBindingOf(): VB {
    return DataBindingUtil.bind(view!!)!!
}

inline fun <reified VM : ViewModel> Fragment.viewModelOf(useActivity: Boolean = false): Lazy<VM> {
    return if (useActivity) {
        activityViewModels { MVVMViewModelFactory(requireActivity(), lifecycle) }
    } else {
        viewModels { MVVMViewModelFactory(context!!, lifecycle) }
    }
}

fun MVVMBaseFragment.bindBaseLiveData(vm: MVVMBaseViewModel) {
    vm.toastLiveData.observe(this, Observer { context?.showToast(it) })
}