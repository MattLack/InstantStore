package ufrpe.mobile.instantstore.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufrpe.mobile.instantstore.MainScreenActivity
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.adapter.PhotoAdapter
import ufrpe.mobile.instantstore.model.Photo

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
        lv.layoutManager = GridLayoutManager(requireContext(),3)

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
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        fun newInstance() = FragmentHome()
    }


}