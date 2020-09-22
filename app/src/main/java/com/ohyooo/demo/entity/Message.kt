package com.ohyooo.demo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val mid: Long,
    @ColumnInfo(name = "from") val from: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "timestamp") val timestamp: Long?,
)
