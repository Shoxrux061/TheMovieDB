package uz.isytem.themoviedb.presentation.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.ScreenMainBinding
import uz.isytem.themoviedb.presentation.adapter.MainAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment

class MainScreen : BaseFragment(R.layout.screen_main){

    private val binding by viewBinding(ScreenMainBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

        val adapter = MainAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomBar.onItemSelected = { id ->
            when (id) {
                0 -> binding.viewPager.currentItem = 0
                1 -> findNavController().navigate(MainScreenDirections.actionMainScreenToSearchPage())
                2 -> binding.viewPager.currentItem = 2
            }
        }
    }
}
