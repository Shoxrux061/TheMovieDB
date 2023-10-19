package uz.isytem.themoviedb.presentation.detail.cast_detail

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.ScreenActorDetailBinding
import uz.isytem.themoviedb.presentation.base.BaseFragment

class ActorDetailScreen : BaseFragment(R.layout.screen_actor_detail){
    private val binding by viewBinding(ScreenActorDetailBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

    }
}