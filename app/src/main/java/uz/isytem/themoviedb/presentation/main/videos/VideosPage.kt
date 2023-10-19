package uz.isytem.themoviedb.presentation.main.videos

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.PageHomeBinding
import uz.isytem.themoviedb.presentation.base.BaseFragment

class VideosPage : BaseFragment(R.layout.page_videos){
    private val binding by viewBinding(PageHomeBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

    }
}
