package uz.isytem.themoviedb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isytem.themoviedb.data.room.Articles
import uz.isytem.themoviedb.databinding.SaveItemBinding
import uz.isytem.themoviedb.utills.Constants

class SaveAdapter : RecyclerView.Adapter<SaveAdapter.ViewHolder>() {

    lateinit var onClickItem : (Int) -> Unit

    private val data = ArrayList<Articles>()

    fun setData(data:List<Articles>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    fun clearData(){
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: SaveItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Articles){
            binding.filmName.text = data.filmName
            binding.poster.load(Constants.IMAGE_URL.plus(data.posterPath))
            binding.title.text = data.title
            binding.root.setOnClickListener {
                onClickItem.invoke(data.movieId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SaveItemBinding.inflate(
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