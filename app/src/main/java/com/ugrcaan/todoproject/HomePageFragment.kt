package com.ugrcaan.todoproject

import android.R
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.google.android.material.tabs.TabLayout.TabGravity
import com.ugrcaan.todoproject.databinding.FragmentHomePageBinding
import com.ugrcaan.todoproject.view.DateUpdateReceiver
import com.ugrcaan.todoproject.view.TimeTickReceiver
import com.ugrcaan.todoproject.vm.MainVM


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomePageFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainVM
    private lateinit var timeReceiver: TimeTickReceiver
    private lateinit var dateReceiver: DateUpdateReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProvider(this)[MainVM::class.java]

        // Register the receiver
        timeReceiver = TimeTickReceiver(viewModel)
        val timeFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        requireActivity().registerReceiver(timeReceiver, timeFilter)

        dateReceiver = DateUpdateReceiver(viewModel)
        val dateFilter = IntentFilter(Intent.ACTION_DATE_CHANGED)
        requireActivity().registerReceiver(dateReceiver, dateFilter)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the timeDisplay LiveData instance
        viewModel.timeDisplay.observe(viewLifecycleOwner) { formattedTime ->
            binding.timeTextClock.text = formattedTime
        }

        viewModel.dateDisplay.observe(viewLifecycleOwner) { formattedDate ->
            binding.dateText.text = formattedDate
        }

        binding.widget.textTodaysTasks.setOnClickListener {
            slideAnimation(0)
            val childFragmentManager : FragmentManager = childFragmentManager
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(binding.widget.innerContainerFragment.id, WidgetTaskFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.widget.textShopList.setOnClickListener {
            slideAnimation(1)
            val childFragmentManager : FragmentManager = childFragmentManager
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(binding.widget.innerContainerFragment.id, WidgetShopListFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver
        requireActivity().unregisterReceiver(timeReceiver)
        requireActivity().unregisterReceiver(dateReceiver)
    }

    private fun slideAnimation(currentPage: Int) {
        val constrainSet = ConstraintSet()
        constrainSet.clone(binding.widget.lineLayout)
        constrainSet.clear(binding.widget.selectedLine.id, ConstraintSet.START)
        constrainSet.clear(binding.widget.selectedLine.id, ConstraintSet.END)

        if (currentPage == 0) {
            constrainSet.connect(
                binding.widget.selectedLine.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
        } else if (currentPage == 1) {
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

