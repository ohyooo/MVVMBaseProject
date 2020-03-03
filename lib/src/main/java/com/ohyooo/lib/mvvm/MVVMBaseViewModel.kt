package com.ohyooo.lib.mvvm

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MVVMBaseViewModel : ViewModel(), MVVMLifecycle {

    var bundle: Bundle = Bundle()

    val toastLiveData = MutableLiveData<String>()

    fun showToast(msg: String?) {
        toastLiveData.postValue(msg)
    }

    /**
     * Lifecycle Start
     */
    override fun onCreate() {
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }
    /**
     * Lifecycle End
     */
}