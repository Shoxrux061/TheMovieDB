package uz.isytem.themoviedb.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.isytem.themoviedb.presentation.main.home.HomePage
import uz.isytem.themoviedb.presentation.main.saved.SavedPage
import uz.isytem.themoviedb.presentation.main.search.SearchPage
import uz.isytem.themoviedb.presentation.main.videos.VideosPage

class MainAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomePage()
            1-> SearchPage()
            2 -> SavedPage()
            else-> HomePage()

        }
    }
}