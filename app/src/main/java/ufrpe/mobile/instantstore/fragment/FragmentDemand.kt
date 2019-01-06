package ufrpe.mobile.instantstore.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ufrpe.mobile.instantstore.R


class FragmentDemand : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val title = view.findViewById<TextView>(R.id.title)

        title.text = "Fragment Message"
        //title.setTextColor(resources.getColor(R.color.colorAccent, null))

        return view
    }


}