package com.ohyooo.demo.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ohyooo.demo.app.App
import com.ohyooo.demo.dao.MsgDao
import com.ohyooo.demo.entity.Message

@Database(entities = arrayOf(Message::class), version = 1)
abstract class MsgDB : RoomDatabase() {
    abstract fun msgDao(): MsgDao
}

object DB {
    val msgDb = Room.databaseBuilder(App.instance, MsgDB::class.java, "messages").build()
}