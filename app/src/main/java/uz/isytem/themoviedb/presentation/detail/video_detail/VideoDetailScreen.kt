package uz.isytem.themoviedb.presentation.detail.video_detail

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.ScreenVideoDetailBinding
import uz.isytem.themoviedb.presentation.base.BaseFragment

class VideoDetailScreen : BaseFragment(R.layout.screen_video_detail){
    private val binding by viewBinding(ScreenVideoDetailBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

    }
}