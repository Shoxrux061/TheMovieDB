package uz.isytem.themoviedb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isytem.themoviedb.data.models.detail.Genre
import uz.isytem.themoviedb.data.models.popular.PopularResult
import uz.isytem.themoviedb.databinding.GenerItemBinding
import uz.isytem.themoviedb.databinding.ItemBottomBinding
import uz.isytem.themoviedb.utills.Constants

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    private val data = ArrayList<Genre>()

    fun setData(data:List<Genre>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: GenerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Genre){
            binding.genreName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}