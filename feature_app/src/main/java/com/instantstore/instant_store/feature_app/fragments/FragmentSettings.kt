package com.instantstore.instant_store.feature_app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.instantstore.instant_store.feature_app.R

class FragmentSettings : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_1,container, false)

        /*data = ArrayList()

        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))
        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))
        data.add(Photo(R.drawable.caio,"caio"))
        data.add(Photo(R.drawable.mateus,"mateus"))
*/
        //globalContext = this.activity;

        // Creates a vertical Layout Manager
        //list_home.layoutManager = LinearLayoutManager(requireContext())

        // Access the RecyclerView Adapter and load the data into it
        //list_home.adapter = PhotoAdapter(data, requireContext())


        val title = view.findViewById<TextView>(R.id.title)

        title.text = "Fragment Message"
            //title.setTextColor(resources.getColor(colorAccent, null))

        return view
    }

    companion object {
        fun newInstance() = FragmentHome()
    }

}