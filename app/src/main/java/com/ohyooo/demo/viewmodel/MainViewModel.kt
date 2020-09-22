package com.ohyooo.demo.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ohyooo.demo.db.DB
import com.ohyooo.demo.entity.Message
import com.ohyooo.demo.model.MainUIItem
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class MainViewModel : MVVMBaseViewModel() {
    val ui = MainUIItem()

    fun insertMessage() = viewModelScope.launch(IO) {
        DB.msgDb.msgDao().insertAll(Message(0, "123", "content", System.currentTimeMillis()))
    }

    fun deleteMessage() = viewModelScope.launch(IO) {
        DB.msgDb.msgDao().deleteAll()
    }

    fun listAllMessages() = viewModelScope.launch(IO) {
        val list = DB.msgDb.msgDao().getAll()
        Timber.e(Gson().toJson(list))
    }
}