package ufrpe.mobile.instantstore.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.ArrayAdapter
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.model.Photo


class UploadPhotoAdapter (private val posts: ArrayList<Photo>, private val context: Activity) : ArrayAdapter<Photo>(context,R.layout.fragment_upload, posts){

    val layoutInflater = context.layoutInflater

    val customView = layoutInflater.inflate(R.layout.photo_detail,null,true)!!


   // customView


   // customView.customUserName.text = "Autor: ${posts[position].author}"
    //customView.customCommentText.text = posts[position].cityName

    //Picasso
        //.with(context)
        //.load(posts[position].image)
        //.into(customView.customImageView)

    //return customView
}