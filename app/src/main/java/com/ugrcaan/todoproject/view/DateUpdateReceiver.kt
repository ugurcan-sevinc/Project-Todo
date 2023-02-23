package com.ugrcaan.todoproject.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import com.ugrcaan.todoproject.vm.MainVM
import java.util.*

class DateUpdateReceiver(private val viewModel: MainVM) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_DATE_CHANGED) {
            val currentDate = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)
            viewModel.updateDateDisplay(formattedDate)
        }
    }
}
