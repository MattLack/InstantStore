package ufrpe.mobile.instantstore.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_entry.view.*
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.model.Photo


class UploadPhotoAdapter (private val posts: ArrayList<Photo>, private val context: Activity) : ArrayAdapter<Photo>(context,R.layout.photo_entry, posts){

    @SuppressLint("ViewHolder", "InflateParams", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.photo_entry,null,true)

        customView.tvName.text = "Autor: ${posts[position].txt}"

        Picasso
            .with(context)
            .load(posts[position].img)
            .into(customView.imgId)

        return customView
    }
}