package com.ohyooo.lib.mvvm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

interface MVVMLifecycle : DefaultLifecycleObserver {
    fun onCreate() {}

    fun onPause() {}

    fun onResume() {}

    fun onDestroy() {}

    override fun onCreate(owner: LifecycleOwner) = onCreate()

    override fun onPause(owner: LifecycleOwner) = onPause()

    override fun onResume(owner: LifecycleOwner) = onResume()

    override fun onDestroy(owner: LifecycleOwner) = onDestroy()
}
