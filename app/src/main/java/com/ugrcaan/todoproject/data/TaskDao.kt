package com.ugrcaan.todoproject.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table OR DER BY id ASC")
    fun readAllTasks(): LiveData<List<Task>>
}