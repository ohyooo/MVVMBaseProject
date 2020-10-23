package com.ohyooo.demo.ui.main

import android.os.Bundle
import com.ohyooo.demo.R
import com.ohyooo.demo.databinding.ActivityMainBinding
import com.ohyooo.demo.viewmodel.MainViewModel
import com.ohyooo.lib.extension.viewDataBindingOf
import com.ohyooo.lib.extension.viewModelOf
import com.ohyooo.lib.mvvm.MVVMBaseActivity

class MainActivity : MVVMBaseActivity() {

    private val vm: MainViewModel by viewModelOf()
    private val vdb by lazy { ActivityMainBinding.inflate(layoutInflater).also { it.vm = vm } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vdb.root)

        initData()
        initViews()
    }

    private fun initData() {
    }

    private fun initViews() {
        vdb.button.setOnClickListener {

        }
    }
}