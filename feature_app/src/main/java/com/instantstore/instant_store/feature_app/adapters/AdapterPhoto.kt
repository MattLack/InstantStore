package com.instantstore.instant_store.feature_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.instantstore.instant_store.feature_app.R
import com.instantstore.instant_store.feature_app.models.Photo

class AdapterPhoto (var context:Context, var data:ArrayList<Photo>): BaseAdapter() {

    lateinit var img:ImageView
    lateinit var txt:TextView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.photo_entry,parent,false)

        img = view.findViewById(R.id.imgId)
        txt = view.findViewById(R.id.tvName)

        img.setImageResource(data.get(position).img)
        txt.text = data.get(position).txt

        return view

    }

    override fun getItem(position: Int): Any {
        return data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }


}