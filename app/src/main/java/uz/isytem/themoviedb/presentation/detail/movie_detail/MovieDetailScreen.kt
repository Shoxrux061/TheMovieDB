package uz.isytem.themoviedb.presentation.detail.movie_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.data.room.Articles
import uz.isytem.themoviedb.data.room.RoomDataBase
import uz.isytem.themoviedb.databinding.ScreenMovieDetailBinding
import uz.isytem.themoviedb.presentation.adapter.GenreAdapter
import uz.isytem.themoviedb.presentation.adapter.RecommendedAdapter
import uz.isytem.themoviedb.presentation.adapter.SimilarAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment
import uz.isytem.themoviedb.presentation.detail.DetailViewModel
import uz.isytem.themoviedb.utills.Constants
import java.util.Locale

class MovieDetailScreen : BaseFragment(R.layout.screen_movie_detail) {
    private val binding by viewBinding(ScreenMovieDetailBinding::bind)
    private var success = false
    private var isSaved = false
    private val args: MovieDetailScreenArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private val similarAdapter by lazy {
        SimilarAdapter()
    }
    private val recAdapter by lazy {
        RecommendedAdapter()
    }
    private val genreAdapter by lazy {
        GenreAdapter()
    }

    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        viewModel.getDetail(args.movieId)
        viewModel.getSimilar(args.movieId)
        viewModel.getRecommended(args.movieId)
        observe()
        setActions()
    }


    private fun setActions() {
        similarAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MovieDetailScreenDirections.actionMovieDetailScreenSelf(
                    id,
                    pop
                )
            )
        }
        recAdapter.onClickItem = { id: Int, pop: String ->
            findNavController().navigate(
                MovieDetailScreenDirections.actionMovieDetailScreenSelf(
                    id,
                    pop
                )
            )
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(MovieDetailScreenDirections.actionMovieDetailScreenToMainScreen())
        }
    }

    private fun setAdapter() {
        binding.similarRecycle.adapter = similarAdapter
        binding.recRecycle.adapter = recAdapter
        binding.genersRecycler.adapter = genreAdapter
    }

    private fun observe() {
        viewModel.successResponse.observe(viewLifecycleOwner) {

            success = true
            checkRequest()
            binding.background.load(Constants.IMAGE_URL.plus(it!!.posterPath))
            binding.poster.load(Constants.IMAGE_URL.plus(it.posterPath))
            binding.lang.text = it.originalLanguage.uppercase(Locale.getDefault())
            binding.originalTitle.text = it.originalTitle
            binding.overview.text = it.overview
            binding.realeseDate.text = it.releaseDate
            binding.budget.text = it.revenue.toString()
            binding.pop.text = args.pop
            binding.tagline.text = it.tagline
            genreAdapter.setData(it.genres)
            binding.filmName.text = it.title

            val data = Articles(
                movieId = it.id,
                posterPath = it.posterPath,
                filmName = it.title,
                title = it.tagline
            )
            binding.saveBtn.setOnClickListener {
                isSaved = if (isSaved) {
                    RoomDataBase.getInstance()?.deleteById(data.movieId)
                    binding.saveBtn.setImageResource(R.drawable.bookmark_not_saved)
                    false
                } else {
                    binding.saveBtn.setImageResource(R.drawable.bookmark_saved)
                    RoomDataBase.getInstance()?.addMovie(data)
                    true
                }
            }
            if (RoomDataBase.getInstance()?.getAllIds()!!.contains(it.id)) {
                isSaved = true
                binding.saveBtn.setImageResource(R.drawable.bookmark_saved)
            } else {
                isSaved = false
                binding.saveBtn.setImageResource(R.drawable.bookmark_not_saved)

            }
        }
        viewModel.successResponseRecommended.observe(viewLifecycleOwner) {
            recAdapter.setData(it!!.results)
            success = true
            checkRequest()
        }
        viewModel.successResponseSimilar.observe(viewLifecycleOwner) {
            similarAdapter.setData(it!!.results)
            success = true
            checkRequest()
        }
    }

    private fun checkRequest() {
        if (success) {
            binding.content.visibility = View.VISIBLE
            binding.shimmer.visibility = View.GONE
        }
    }
}