package com.example.vkapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.model.ModelMain
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.*


//для класса fragment_news.xml -> news_item

class NewsAdapter(private var newsList: ModelMain) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imAvatar: ImageView? = null
        var tvAuthorName: TextView? = null
        var tvDate: TextView? = null
        var tvNewsContent: TextView? = null
        val picasso: Picasso = Picasso.get()
        var groupIcon:  String = ""

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

    override fun getItemCount() = newsList.response.items.size


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.groupIcon = newsList.response.groups[position].photo_100
        holder.picasso.load(holder.groupIcon).into(holder.imAvatar)
        holder.tvAuthorName?.text = newsList.response.groups[position].name
        holder.tvNewsContent?.text = newsList.response.items[position].text



    }

}