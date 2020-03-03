package com.ohyooo.demo.ui.main

import android.os.Bundle
import android.view.View
import com.ohyooo.demo.R
import com.ohyooo.demo.databinding.FragmentMainBinding
import com.ohyooo.demo.viewmodel.MainViewModel
import com.ohyooo.lib.extension.viewDataBindingOf
import com.ohyooo.lib.extension.viewModelOf
import com.ohyooo.lib.mvvm.MVVMBaseFragment

class MainFragment : MVVMBaseFragment(R.layout.fragment_main) {

    // bind to current fragment
    private val mViewModel: MainViewModel by viewModelOf()

    // bind to activity
    private val mMainViewModel: MainViewModel by viewModelOf(true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = viewDataBindingOf<FragmentMainBinding>()
        db.vm = mViewModel
        db.activityVM = mMainViewModel
        initData()
    }

    private fun initData() {
        mViewModel.sendByFragment()
    }
}