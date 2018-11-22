package com.instantstore.instant_store.feature_app.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.instantstore.instant_store.feature_app.R
import com.instantstore.instant_store.feature_app.models.Photo
import kotlinx.android.synthetic.main.photo_entry.view.*

class PhotoAdapter(val photos : ArrayList<Photo>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return photos.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_entry,parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgView.setImageResource(photos.get(position).img)
        holder.texto.text = "testando"
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val imgView: ImageView = view.imgId
    val texto: TextView = view.tvName
}
