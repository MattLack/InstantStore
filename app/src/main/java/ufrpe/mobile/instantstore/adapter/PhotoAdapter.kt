package ufrpe.mobile.instantstore.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.photo_entry.view.*
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.model.Photo


class PhotoAdapter(val photos: ArrayList<Photo>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return photos.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_entry, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.imgView.setImageResource( photos.get(position).img )
        //holder.texto.text = "testando"
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val imgView: ImageView = view.imgId
    //val texto: TextView = view.tvName
}
