package uz.isytem.themoviedb.presentation.main.saved

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.data.room.RoomDataBase
import uz.isytem.themoviedb.databinding.PageSavedBinding
import uz.isytem.themoviedb.presentation.adapter.SaveAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment
import uz.isytem.themoviedb.presentation.main.MainScreenDirections
import uz.isytem.themoviedb.presentation.main.search.SearchPageDirections

class SavedPage : BaseFragment(R.layout.page_saved){
    private val binding by viewBinding(PageSavedBinding::bind)
    private val adapter by lazy{
        SaveAdapter()
    }
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        setAdapterAndData()
        setActions()
    }

    private fun setActions() {
        adapter.onClickItem={
            findNavController().navigate(MainScreenDirections.actionMainScreenToMovieDetailScreen(it,""))
        }
    }

    private fun setAdapterAndData() {
        binding.recyclerView.adapter = adapter

        adapter.clearData()
        adapter.setData(RoomDataBase.getInstance()!!.getAllSaved())
    }
}