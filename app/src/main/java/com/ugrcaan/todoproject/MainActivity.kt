package com.ugrcaan.todoproject

import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ugrcaan.todoproject.databinding.ActivityMainBinding
import com.ugrcaan.todoproject.vm.MainVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavbarButtonsOnClickListeners(binding.navbar.mainPage, HomePageFragment::class.java)
        setNavbarButtonsOnClickListeners(binding.navbar.todoList, TodoListFragment::class.java)
        setNavbarButtonsOnClickListeners(binding.navbar.shopList, ShopListFragment::class.java)
        setNavbarButtonsOnClickListeners(binding.navbar.calendar, CalendarFragment::class.java)

    }

    private fun setNavbarButtonsOnClickListeners(navbarItem: View, fragmentClass: Class<*>) {
        navbarItem.setOnClickListener{
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = fragmentClass.newInstance() as Fragment
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            binding.navbar.mainPage.background.setTint(resources.getColor(R.color.navbarItem_light_gray))
            binding.navbar.todoList.background.setTint(resources.getColor(R.color.navbarItem_light_gray))
            binding.navbar.shopList.background.setTint(resources.getColor(R.color.navbarItem_light_gray))
            binding.navbar.calendar.background.setTint(resources.getColor(R.color.navbarItem_light_gray))

            it.background.setTint(resources.getColor(R.color.navbarItem_dark_gray))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}