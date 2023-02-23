package com.ugrcaan.todoproject.vm

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainVM : ViewModel() {


    private val _timeDisplay = MutableLiveData<String>()
    val timeDisplay: LiveData<String> = _timeDisplay

    private val _dateDisplay = MutableLiveData<String>()
    private val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
    val dateDisplay: LiveData<String> = MutableLiveData(dateFormat.format(Calendar.getInstance().time))

    fun updateTimeDisplay(formattedTime: String) {
        _timeDisplay.value = formattedTime
    }

    fun updateDateDisplay(formattedDate: String) {
        _dateDisplay.value = formattedDate
    }

}