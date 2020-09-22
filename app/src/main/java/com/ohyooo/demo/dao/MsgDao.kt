package com.ohyooo.demo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ohyooo.demo.entity.Message

@Dao
interface MsgDao {
    @Query("SELECT * FROM messages")
    fun getAll(): List<Message>

    @Query("SELECT * FROM messages WHERE mid IN (:msgIds)")
    fun loadAllByIds(msgIds: IntArray): List<Message>

    @Query("SELECT * FROM messages WHERE `from` IN (:froms)")
    fun loadAllByFroms(froms: IntArray): List<Message>

    @Query("SELECT * FROM messages WHERE `from` LIKE :from LIMIT 1")
    fun findByFrom(from: String): Message

    @Insert
    fun insertAll(vararg users: Message)

    @Delete
    fun delete(user: Message)

    @Query("DELETE FROM messages")
    fun deleteAll()

    @Query("DELETE FROM messages WHERE mid IN (:ids)")
    fun deleteByIds(ids: IntArray)
}