package com.example.vkapp

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.model_package.model.ModelMain
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.time.Instant
import java.time.LocalDateTime
import java.util.*


//для класса fragment_news.xml -> news_item

class NewsAdapter(private var newsList: ModelMain, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private var itemListener: ((String, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        itemListener = listener
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imAvatar: ImageView? = null
        var tvAuthorName: TextView? = null
        var tvDate: TextView? = null
        var tvNewsContent: TextView? = null
        val picasso: Picasso = Picasso.get()
        var retrofitBuild = RetrofitCreator().getUserAccessToken()
        var user_ids = ""
        var date: Long = 0
        var root: ConstraintLayout? = null
        var postID: String = ""
        var owher_id: String = ""

        init {
            imAvatar = itemView.findViewById(R.id.im_profile_id)
            tvAuthorName = itemView.findViewById(R.id.tv_postAuthor_id)
            tvDate = itemView.findViewById(R.id.tv_date_id)
            tvNewsContent = itemView.findViewById(R.id.tv_post_id)
            root = itemView.findViewById(R.id.root)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.news_item, parent, false)
        return NewsHolder(layout)
    }

    override fun getItemCount() = newsList.response.items.size


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
//        holder.tvNewsContent?.text = newsList.response.items[position].text
        holder.user_ids = newsList.response.items[position].from_id.toString()
        holder.tvNewsContent?.text = newsList.response.items[position].text

        //send param to PostFragment for create Retrofit response
        holder.postID = newsList.response.items[position].id.toString()
        holder.owher_id = newsList.response.items[position].owner_id.toString()

        holder.root?.setOnClickListener {
            itemListener?.invoke(
                holder.owher_id,
                holder.postID
            )

        }
        if (holder.tvNewsContent?.text == "") {
            holder.tvNewsContent?.text = "Post text empty"
        }

        //view Date
        holder.date = (newsList.response.items[position].date).toLong()
        holder.tvDate?.text = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(holder.date * 1000),
            TimeZone.getDefault().toZoneId()
        ).toString().substring(0, 10)

        //get user photo and name
        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            try {
                val responseAdapter = holder.retrofitBuild.getPhoto(
                    holder.user_ids,
                    "photo_100",
                    getToken,
                    VERSION
                ).awaitResponse()
                if (responseAdapter.isSuccessful) {
                    Log.d("CWWError", responseAdapter.message().toString())
                    val data = responseAdapter.body()!!
                    holder.tvAuthorName?.text =
                        data.response[0].first_name + " " + data.response[0].last_name
                    holder.picasso.load(data.response[0].photo_100).into(holder.imAvatar)
                }
            } catch (e: Exception) {
                Log.d("CWWW", "ERROR")
                Toast.makeText(context, "Please scroll slowly", Toast.LENGTH_SHORT).show()
            }
        }
    }
}