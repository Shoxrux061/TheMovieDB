package uz.isytem.themoviedb.presentation.main.search


import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isytem.themoviedb.R
import uz.isytem.themoviedb.databinding.PageSearchBinding
import uz.isytem.themoviedb.presentation.adapter.SearchAdapter
import uz.isytem.themoviedb.presentation.base.BaseFragment

class SearchPage : BaseFragment(R.layout.page_search) {
    private val binding by viewBinding(PageSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private var isLoading = false
    private val adapter by lazy{
        SearchAdapter()
    }
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        search()
        setAdapter()
        observe()
        setActions()

        adapter.onPaginate = {
            if(!isLoading) {
                isLoading = true
                viewModel.searchMovie(binding.input.text.toString().trim())
            }
        }
    }
    private fun setActions() {
        adapter.onClickItem = { id:Int,pop:String->
            findNavController().navigate(SearchPageDirections.actionSearchPageToMovieDetailScreen(id,pop))
        }
    }

    private fun setAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(context,3)
    }
    private fun search() {
        binding.searchBtn.setOnClickListener {
            if (binding.input.text.isNotBlank() && binding.input.text.isNotEmpty()) {
                adapter.clearData()
                viewModel.searchMovie(binding.input.text.toString().trim())
            } else {
                binding.input.error = "Input please"
            }
        }

        binding.input.addTextChangedListener {

            val text = it.toString().trim()

           if (text.isNotEmpty() && text.isNotBlank()){
               adapter.clearData()
               viewModel.searchMovie(text)
           }
            else{
                adapter.clearData()
           }
        }
    }
    private fun observe() {
        viewModel.successResponseSearch.observe(viewLifecycleOwner){
            adapter.setData(it!!.results)
            isLoading = false

        }
    }
}
