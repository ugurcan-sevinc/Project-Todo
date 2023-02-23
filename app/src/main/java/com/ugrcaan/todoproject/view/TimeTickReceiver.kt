package com.ugrcaan.todoproject.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import com.ugrcaan.todoproject.vm.MainVM
import java.util.*

class TimeTickReceiver(private val viewModel: MainVM) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_TIME_TICK) {
            val currentTime = Calendar.getInstance().time
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val formattedTime = timeFormat.format(currentTime)
            viewModel.updateTimeDisplay(formattedTime)
        }
    }
}