package com.example.vkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PostPhotoAdapter(
    private var photoResList: MutableList<Int>,
    private val fragment: NewsFragment
) :
    RecyclerView.Adapter<PostPhotoAdapter.NewsHolder>() {

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imPostPhoto: ImageView? = null


        init {
            imPostPhoto = itemView.findViewById(R.id.im_postItem_id)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.post_image_item, parent, false)

        return NewsHolder(layout)
    }

    override fun getItemCount(): Int {
        return photoResList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val photo = photoResList[position]
        photo.let { holder.imPostPhoto?.setImageResource(it) }

    }

}