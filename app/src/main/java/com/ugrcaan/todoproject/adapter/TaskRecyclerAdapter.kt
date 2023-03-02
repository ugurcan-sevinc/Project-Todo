package com.ugrcaan.todoproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugrcaan.todoproject.R

class TaskRecyclerAdapter : RecyclerView.Adapter<TaskRecyclerAdapter.RowHolder>(){

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_widget_task_row, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 5
    }


}