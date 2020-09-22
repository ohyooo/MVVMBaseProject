package com.ohyooo.demo.ui.main

import android.os.Bundle
import com.ohyooo.demo.R
import com.ohyooo.demo.databinding.ActivityMainBinding
import com.ohyooo.demo.db.DB
import com.ohyooo.demo.entity.Message
import com.ohyooo.demo.viewmodel.MainViewModel
import com.ohyooo.lib.extension.viewDataBindingOf
import com.ohyooo.lib.extension.viewModelOf
import com.ohyooo.lib.mvvm.MVVMBaseActivity

class MainActivity : MVVMBaseActivity(R.layout.activity_main) {

    private val vm: MainViewModel by viewModelOf()
    private val vdb by lazy { viewDataBindingOf<ActivityMainBinding>().also { it.vm = vm } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        vdb.insert.setOnClickListener {
            vm.insertMessage()
        }
        vdb.delete.setOnClickListener {
            vm.deleteMessage()
        }
        vdb.list.setOnClickListener {
            vm.listAllMessages()
        }
    }
}