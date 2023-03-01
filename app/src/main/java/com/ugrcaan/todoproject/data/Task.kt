package com.ugrcaan.todoproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val header: String,
    val description: String,
    val isDone: Boolean,
    val isFavourite: Boolean,
    val taskDueDate: String,
    val taskDueTime: String
    )