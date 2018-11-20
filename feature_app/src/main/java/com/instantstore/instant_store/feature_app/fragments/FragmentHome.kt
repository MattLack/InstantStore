package com.instantstore.instant_store.feature_app.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.instantstore.instant_store.feature_app.R
import com.instantstore.instant_store.feature_app.adapters.AdapterPhoto
import com.instantstore.instant_store.feature_app.models.Photo
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.layout_center_view_profile.*

class FragmentHome : Fragment() {

    lateinit var lv: GridView
    lateinit var adapter: AdapterPhoto
    lateinit var data: ArrayList<Photo>
    private var globalContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile,container, false)


//        data = ArrayList()
//
//        data.add(Photo(R.drawable.caio,"caio"))
//        data.add(Photo(R.drawable.mateus,"mateus"))
//        data.add(Photo(R.drawable.caio,"caio"))
//        data.add(Photo(R.drawable.mateus,"mateus"))
//        data.add(Photo(R.drawable.caio,"caio"))
//        data.add(Photo(R.drawable.mateus,"mateus"))
//
//        globalContext = this.activity;
//
//        adapter = AdapterPhoto(this.globalContext!!,data)
//
//        gridViewPhotos.adapter = adapter

        //val title = view.findViewById<TextView>(R.id.title)
        //title.text = "Fragment Home"
        //title.setTextColor(resources.getColor(colorAccent, null))

        return view
    }






}