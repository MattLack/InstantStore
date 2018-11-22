package com.instantstore.instant_store.feature_app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instantstore.instant_store.feature_app.R
import com.instantstore.instant_store.feature_app.adapters.PhotoAdapter
import com.instantstore.instant_store.feature_app.models.Photo

class FragmentHome : Fragment() {

    lateinit var lv: RecyclerView
    lateinit var adapter: PhotoAdapter
    lateinit var data: ArrayList<Photo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile,container, false)

        data = ArrayList()

        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))
        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))
        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))

        lv = view.findViewById(R.id.list_home)

        // Creates a vertical Layout Manager
        lv.layoutManager = LinearLayoutManager(requireContext())

        // Access the RecyclerView Adapter and load the data into it
        lv.adapter = PhotoAdapter(data, requireContext())

    /*
        val title = view.findViewById<TextView>(R.id.title)
        title.text = "Fragment Home"
        title.setTextColor(resources.getColor(colorAccent, null))
**/
        return view
    }

    companion object {
        fun newInstance() = FragmentHome()
    }


}