package com.ohyooo.lib.mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.ohyooo.lib.livedata.SingleLiveEvent

abstract class MVVMBaseViewModel : ViewModel(), MVVMLifecycle {

    var bundle: Bundle = Bundle()

    val toastLiveData = SingleLiveEvent<String>()

    fun showToast(msg: String?) {
        toastLiveData.postValue(msg)
    }

    /**
     * Lifecycle Start
     */
    override fun onCreate() {}

    override fun onPause() {}

    override fun onResume() {}

    override fun onDestroy() {}
    /**
     * Lifecycle End
     */
}