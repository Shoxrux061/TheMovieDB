package uz.isytem.themoviedb.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.ScreenSplashBinding
import uz.isytem.themoviedb.presentation.base.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment(R.layout.screen_splash){
    private val binding by viewBinding(ScreenSplashBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

    }
}