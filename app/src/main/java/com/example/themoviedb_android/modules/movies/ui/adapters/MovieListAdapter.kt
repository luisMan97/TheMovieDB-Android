package com.example.themoviedb_android.modules.movies.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb_android.R
import com.example.themoviedb_android.databinding.ItemMovieBinding
import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants.ENDPOINT_IMAGE
import com.example.themoviedb_android.modules.movies.domain.Movie
import com.example.themoviedb_android.utils.extensions.bindImageUrl
import com.example.themoviedb_android.utils.extensions.bindingInflate
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(
    private val listener: (Movie) -> Unit
): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val movieList: MutableList<Movie> = mutableListOf()
    //private var movieList = ArrayList<Movie>()

    fun updateData(newData: List<Movie>) {
        movieList.clear()
        movieList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
         ViewHolder(
             //parent.bindingInflate(viewType, false),
             parent.bindingInflate(R.layout.item_movie, false),
            listener
        )

    override fun getItemCount() = movieList.size

    override fun getItemId(position: Int): Long = movieList[position].id.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ViewHolder(
        private val dataBinding: ItemMovieBinding,
        private val listener: (Movie) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(item: Movie){
            dataBinding.movie = item
            itemView.movie_image.bindImageUrl(
                url = "$ENDPOINT_IMAGE${item.posterPath}",
                placeholder = R.drawable.ic_baseline_movie_24,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.setOnClickListener { listener(item) }
        }

    }

}