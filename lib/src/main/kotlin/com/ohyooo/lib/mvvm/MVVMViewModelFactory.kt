package com.ohyooo.lib.mvvm

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ohyooo.lib.extension.bindBaseLiveData

class MVVMViewModelFactory(private val owner: Any, private val lifecycle: Lifecycle) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val clazz = modelClass.getDeclaredConstructor().newInstance()
        if (clazz is MVVMBaseViewModel) {
            lifecycle.addObserver(clazz)
            if (owner is MVVMBaseActivity) {
                owner.bindBaseLiveData(clazz)
                clazz.bundle = owner.intent.extras ?: Bundle()
            } else if (owner is MVVMBaseFragment) {
                owner.bindBaseLiveData(clazz)
                clazz.bundle = owner.arguments ?: Bundle()
            }
        }
        return clazz
    }
}
