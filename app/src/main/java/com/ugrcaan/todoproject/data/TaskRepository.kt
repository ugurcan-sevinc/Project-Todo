package com.ugrcaan.todoproject.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val readAllTasks: LiveData<List<Task>> = taskDao.readAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

}