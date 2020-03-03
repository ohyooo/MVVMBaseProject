package com.ohyooo.demo.ui.main

import android.os.Bundle
import com.ohyooo.demo.R
import com.ohyooo.demo.databinding.ActivityMainBinding
import com.ohyooo.demo.viewmodel.MainViewModel
import com.ohyooo.lib.extension.replaceFragment
import com.ohyooo.lib.extension.viewDataBindingOf
import com.ohyooo.lib.extension.viewModelOf
import com.ohyooo.lib.mvvm.MVVMBaseActivity

class MainActivity : MVVMBaseActivity(R.layout.activity_main) {

    private val mViewModel: MainViewModel by viewModelOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = viewDataBindingOf<ActivityMainBinding>()
        db.vm = mViewModel
        initData()
        initViews()
    }

    private fun initData() {
        mViewModel.sendByActivity()
    }

    private fun initViews() {
        replaceFragment(R.id.container, MainFragment())
    }
}