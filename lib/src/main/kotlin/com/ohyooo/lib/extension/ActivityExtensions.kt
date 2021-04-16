package com.ohyooo.lib.extension

import android.app.Activity
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ohyooo.lib.mvvm.MVVMBaseActivity
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import com.ohyooo.lib.mvvm.MVVMViewModelFactory

inline fun <reified VB : ViewDataBinding> Activity.viewDataBindingOf(): VB {
    return DataBindingUtil.findBinding((findViewById<ViewGroup>(android.R.id.content)).getChildAt(0))!!
}

inline fun <reified VM : ViewModel> ComponentActivity.viewModelOf(): Lazy<VM> {
    return viewModels { MVVMViewModelFactory(this, lifecycle) }
}

fun FragmentActivity.replaceFragment(id: Int, fragment: Fragment, tag: String? = null) {
    val fm = supportFragmentManager
    val ft = fm.beginTransaction()
    ft.replace(id, fragment, tag)
    ft.commit()
}

fun MVVMBaseActivity.bindBaseLiveData(vm: MVVMBaseViewModel) {
    vm.toastLiveData.observe(this, Observer { showToast(it) })
}