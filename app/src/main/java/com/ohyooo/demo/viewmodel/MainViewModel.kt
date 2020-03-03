package com.ohyooo.demo.viewmodel

import androidx.lifecycle.viewModelScope
import com.ohyooo.demo.model.MainUIItem
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import com.ohyooo.network.repository.GithubAPIRepository
import kotlinx.coroutines.launch

class MainViewModel : MVVMBaseViewModel() {
    val ui = MainUIItem()

    override fun onCreate() {
    }

    fun sendByActivity() {
        viewModelScope.launch {
            val resp = GithubAPIRepository.getRateLimit()
            if (resp.errorCode != 0) {
                showToast(resp.errorMsg)
            } else {
                ui.coreRemaining.set("${resp.resources?.core?.remaining ?: 0}")
            }
        }
    }

    fun sendByFragment() {
        viewModelScope.launch {
            val resp = GithubAPIRepository.getRateLimit()
            if (resp.errorCode != 0) {
                showToast(resp.errorMsg)
            } else {
                ui.searchRemaining.set("${resp.resources?.search?.remaining ?: 0}")
            }
        }
    }
}