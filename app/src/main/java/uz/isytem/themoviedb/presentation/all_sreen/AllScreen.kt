package uz.isytem.themoviedb.presentation.all_sreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.data.models.all_screen.AllScreenData
import uz.isytem.themoviedb.databinding.ScreenAllBinding
import uz.isytem.themoviedb.presentation.adapter.AllScreenAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment

class AllScreen : BaseFragment(R.layout.screen_all) {
    private val binding by viewBinding(ScreenAllBinding::bind)
    private val adapter by lazy {
        AllScreenAdapter()
    }
    private var isLoading = false
    private val args: AllScreenArgs by navArgs()
    private val viewModel: AllViewModel by viewModels()
    override fun onCreate(view: View, savedInstanceState: Bundle?) {

        setAdapter()
        loadData()
        setActions()

        adapter.onPaginate={
            if (!isLoading) {
                isLoading = true
                when (args.category) {
                    "Popular" -> {
                        viewModel.getPopular()
                    }

                    "Top Rated" -> {
                        viewModel.getTopRated()
                    }

                    "Upcoming" -> {
                        viewModel.getUpcoming()
                    }

                    "Now Playing" -> {
                        viewModel.getNowPlaying()
                    }
                }
            }
        }
    }

    private fun loadData() {

        when (args.category) {
            "Popular" -> {
                getPopular()
            }

            "Top Rated" -> {
                getTopRated()
            }

            "Upcoming" -> {
                getUpcoming()
            }

            "Now Playing" -> {
                getNowPlaying()
            }
        }
        binding.categor.text = args.category
    }

    private fun setActions() {
        adapter.onClickItem={ id: Int, pop: String ->
            findNavController().navigate(AllScreenDirections.actionAllScreenToMovieDetailScreen(id,pop))
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(AllScreenDirections.actionAllScreenToMainScreen())
        }
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context,3)
    }
    private fun getNowPlaying() {
        viewModel.getNowPlaying()

        viewModel.successResponse.observe(viewLifecycleOwner) {

            val dataList = ArrayList<AllScreenData>()
            isLoading = false
            for (i in 0 until it!!.results.size){
                val data = AllScreenData(
                    posterPath = it.results[i].posterPath,
                    movieId = it.results[i].id,
                    title = it.results[i].overview,
                    filmName = it.results[i].title,
                    voteAverage = it.results[i].voteAverage.toString()
                )
                dataList.add(data)
            }
            adapter.setData(dataList)
        }
    }
    private fun getUpcoming() {
        viewModel.getUpcoming()

        viewModel.successResponseUpcoming.observe(viewLifecycleOwner){
            val dataList = ArrayList<AllScreenData>()
            isLoading = false
            for (i in 0 until it!!.results.size){
                val data = AllScreenData(
                    posterPath = it.results[i].posterPath,
                    movieId = it.results[i].id,
                    title = it.results[i].overview,
                    filmName = it.results[i].title,
                    voteAverage = it.results[i].voteAverage.toString()
                )
                dataList.add(data)
            }
            adapter.setData(dataList)
        }
    }
    private fun getTopRated() {

        viewModel.getTopRated()

        viewModel.successResponseTopRated.observe(viewLifecycleOwner){
            val dataList = ArrayList<AllScreenData>()
            isLoading = false
            for (i in 0 until  it!!.results.size){
                val data = AllScreenData(
                    posterPath = it.results[i].posterPath,
                    movieId = it.results[i].id,
                    title = it.results[i].overview,
                    filmName = it.results[i].title,
                    voteAverage = it.results[i].voteAverage.toString()
                )
                dataList.add(data)
            }
            adapter.setData(dataList)
        }
    }
    private fun getPopular() {

        viewModel.getPopular()

        viewModel.successResponsePopular.observe(viewLifecycleOwner){
            val dataList = ArrayList<AllScreenData>()
            isLoading = false
            for (i in 0 until it!!.results.size){
                val data = AllScreenData(
                    posterPath = it.results[i].posterPath,
                    movieId = it.results[i].id,
                    title = it.results[i].overview,
                    filmName = it.results[i].title,
                    voteAverage = it.results[i].voteAverage.toString()
                )
                dataList.add(data)
            }
            adapter.setData(dataList)
        }
    }
}