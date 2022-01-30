package com.example.vkapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.vkapp.const.getToken
import com.example.vkapp.databinding.FragmentPostBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class PostFragment : Fragment() {
    private val args by navArgs<PostFragmentArgs>()

    private var _postBinding: FragmentPostBinding? = null
    private val binding get() = _postBinding!!


    var retrofitBuilder = RetrofitCreator().getUserAccessToken()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _postBinding = FragmentPostBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postDetails()
    }


    private fun postDetails() {
        val picasso = Picasso.get()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.getPostInfo(
                    "231970122_2572",
                    getToken
                ).awaitResponse()

                if (response.isSuccessful) {
                    val data = response.body()!!
                    var setImgPos = 0
                    binding.countOfLikes.text = data.response[0].likes.count.toString()

                    binding.imIsLikedId.setOnClickListener {
                        addLike()
                    }

                    if (data.response[0].text != "") {
                        binding.repostTextPageOwner.text = "Author's text: ${data.response[0].text}"
                    }

                    val imageSize =
                        data.response[0].copy_history[0].attachments[0].photo.sizes.size - 1
                    binding.postText.text = data.response[0].copy_history[0].text

                    picasso.load(data.response[0].copy_history[0].attachments[setImgPos].photo.sizes[imageSize].url)
                        .into(binding.imagePostContent)

                    binding.buttonNextPhoto.setOnClickListener {
                        setImgPos += 1
                        if (setImgPos == data.response[0].copy_history[0].attachments.size) {
                            setImgPos = 0
                        }
                        try {
                            picasso.load(data.response[0].copy_history[0].attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        } catch (e: Exception) {
                            Log.d("adaw", "awd")
                            // catch exception if type of attachment is not a photo (video or other)
                            // and revive to 0 position
                            setImgPos = 0
                            picasso.load(data.response[0].copy_history[0].attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        }

                    }

                }
            } catch (e: Exception) {
                Log.d("error", "ERROR")
            }
        }
    }

    private fun addLike() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.addLike(
                    args.postID,
                    getToken
                ).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    binding.countOfLikes.text = data.response.likes.toString()
                    binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
            } catch (e: java.lang.Exception) {
                Log.d("ErrorLike", "Like is already done")
                deleteLike()
            }


        }
    }

    private fun deleteLike() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.deleteLike(
                    args.postID,
                    getToken
                ).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    binding.countOfLikes.text = data.response.likes.toString()
                    binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            } catch (e: java.lang.Exception) {
                Log.d("ErrorLike", "Like is already done")
            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _postBinding = null
    }

}