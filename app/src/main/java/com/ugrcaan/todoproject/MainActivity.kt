package com.ugrcaan.todoproject

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.ugrcaan.todoproject.databinding.ActivityMainBinding
import com.ugrcaan.todoproject.view.DateUpdateReceiver
import com.ugrcaan.todoproject.view.TimeTickReceiver
import com.ugrcaan.todoproject.vm.MainVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainVM
    private lateinit var timeReceiver: TimeTickReceiver
    private lateinit var dateReceiver: DateUpdateReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainVM::class.java]

        // Observe the timeDisplay LiveData instance
        viewModel.timeDisplay.observe(this) { formattedTime ->
            binding.timeTextClock.text = formattedTime
        }

        viewModel.dateDisplay.observe(this) { formattedDate ->
            binding.dateText.text = formattedDate
        }

        // Register the receiver
        timeReceiver = TimeTickReceiver(viewModel)
        val timeFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        registerReceiver(timeReceiver, timeFilter)

        dateReceiver = DateUpdateReceiver(viewModel)
        val dateFilter = IntentFilter(Intent.ACTION_DATE_CHANGED)
        registerReceiver(dateReceiver, dateFilter)

        binding.widget.textTodaysTasks.setOnClickListener {
            slideAnimation(0)
        }

        binding.widget.textShopList.setOnClickListener {
            slideAnimation(1)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver
        unregisterReceiver(timeReceiver)
        unregisterReceiver(dateReceiver)
    }

    private fun slideAnimation(currentPage: Int){
        val constrainSet = ConstraintSet()
        constrainSet.clone(binding.widget.lineLayout)
        constrainSet.clear(binding.widget.selectedLine.id, ConstraintSet.START)
        constrainSet.clear(binding.widget.selectedLine.id, ConstraintSet.END)

        if(currentPage == 0){
            constrainSet.connect(
                binding.widget.selectedLine.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
        }
        else if (currentPage == 1){
            constrainSet.connect(
                binding.widget.selectedLine.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )
        }


        val transition = ChangeBounds()
        transition.interpolator = DecelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.widget.lineLayout, transition)
        constrainSet.applyTo(binding.widget.lineLayout)
    }

}