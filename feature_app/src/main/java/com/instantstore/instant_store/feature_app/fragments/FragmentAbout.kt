package com.instantstore.instant_store.feature_app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.instantstore.instant_store.feature_app.R

class FragmentAbout : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about,container, false)

        val title = view.findViewById<TextView>(R.id.title)

        //title.text = "Fragment About"
        //title.setTextColor(resources.getColor(colorAccent, null))

        return view
    }
}