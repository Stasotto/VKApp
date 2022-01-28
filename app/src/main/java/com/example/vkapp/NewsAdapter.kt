package com.example.vkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.model.ModelMain


//для класса fragment_news.xml -> news_item

class NewsAdapter(private var newsList: MutableList<ModelMain>) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imAvatar: ImageView? = null
        var tvAuthorName: TextView? = null
        var tvDate: TextView? = null
        var tvNewsContent: TextView? = null

        init {
            imAvatar = itemView.findViewById(R.id.im_profile_id)
            tvAuthorName = itemView.findViewById(R.id.tv_postAuthor_id)
            tvDate = itemView.findViewById(R.id.tv_date_id)
            tvNewsContent = itemView.findViewById(R.id.tv_post_id)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.news_item, parent, false)
        return NewsHolder(layout)
    }

    override fun getItemCount() = newsList.size


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

    }

}