package com.example.vkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//для класса fragment_news.xml -> news_item

class NewsAdapter(
    private var newsList: MutableList<NewsToAdapt>,
    private val fragment: NewsFragment
) :
    RecyclerView.Adapter<PostPhotoAdapter.NewsHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostPhotoAdapter.NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.news_item, parent, false)
        return PostPhotoAdapter.NewsHolder(layout)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    override fun onBindViewHolder(holder: PostPhotoAdapter.NewsHolder, position: Int) {
        val news = newsList[position]
        news.let { holder.imAvatar?.setImageResource(it.avatarRes) }
        holder.tvAuthorName?.text = news.authorName
        holder.tvDate?.text = news.newsDate.toString()
        holder.tvNewsContent?.text = news.newsText
    }

}