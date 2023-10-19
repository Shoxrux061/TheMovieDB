package uz.isytem.themoviedb.presentation.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.PageHomeBinding
import uz.isytem.themoviedb.presentation.adapter.PopularAdapter
import uz.isytem.themoviedb.presentation.adapter.TopAdapter
import uz.isytem.themoviedb.presentation.adapter.TopRatedAdapter
import uz.isytem.themoviedb.presentation.adapter.UpcomingAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment
import uz.isytem.themoviedb.presentation.main.MainScreenDirections

class HomePage : BaseFragment(R.layout.page_home) {
    private val binding by viewBinding(PageHomeBinding::bind)
    private val topAdapter = TopAdapter()
    private val popularAdapter = PopularAdapter()
    private val upcomingAdapter = UpcomingAdapter()
    private val topRatedAdapter = TopRatedAdapter()
    private var success = false
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        setAdapters()
        observe()
        setActions()
    }
    private fun checkRequest() {
        if (success){
            binding.shimmer.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
        }
    }

    private fun setActions() {
        topAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToMovieDetailScreen(
                    id,
                    pop
                )
            )
        }
        popularAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToMovieDetailScreen(
                    id,
                    pop
                )
            )
        }
        topRatedAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToMovieDetailScreen(
                    id,
                    pop
                )
            )
        }
        upcomingAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToMovieDetailScreen(
                    id,
                    pop
                )
            )
        }
        binding.popularBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAllScreen("Popular"))
        }
        binding.topRatedBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAllScreen("Top Rated"))
        }
        binding.upcomingBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAllScreen("Upcoming"))
        }
        binding.nowPlayingBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAllScreen("Now Playing"))
        }
    }
    private fun setAdapters() {
        binding.nowPlaying.adapter = topAdapter
        binding.popularRecycle.adapter = popularAdapter
        binding.topRatedRecycle.adapter = topRatedAdapter
        binding.upcomingRecycle.adapter = upcomingAdapter
    }
    private fun observe() {
        viewModel.successResponse.observe(viewLifecycleOwner) {
            success = true
            checkRequest()
            topAdapter.setData(it!!.results)
        }
        viewModel.successResponsePopular.observe(viewLifecycleOwner) {
            success = true
            checkRequest()
            popularAdapter.setData(it!!.results)
        }
        viewModel.successResponseTopRated.observe(viewLifecycleOwner) {
            success = true
            checkRequest()
            topRatedAdapter.setData(it!!.results)
        }
        viewModel.successResponseUpcoming.observe(viewLifecycleOwner) {
            success = true
            checkRequest()
            upcomingAdapter.setData(it!!.results)
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show()
        }
    }
}