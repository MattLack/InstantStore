package ufrpe.mobile.instantstore.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.layout_center_view_profile.*
import ufrpe.mobile.instantstore.MainScreenActivity
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.adapter.PhotoAdapter
import ufrpe.mobile.instantstore.adapter.UploadPhotoAdapter
import ufrpe.mobile.instantstore.model.Photo

class FragmentHome : Fragment() {

    lateinit var lv: RecyclerView
    lateinit var adapter: PhotoAdapter
    var data: ArrayList<Photo> = ArrayList()


    var firebaseDatabase: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var postAdapter: UploadPhotoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile,container, false)

        /*
        lv = view.findViewById(R.id.list_home)

        // Creates a vertical Layout Manager
        lv.layoutManager = GridLayoutManager(requireContext(),3)

        // Access the RecyclerView Adapter and load the data into it
        lv.adapter = PhotoAdapter(data, requireContext())
        */

        // new adapter implementation

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()


        lv = view.findViewById(R.id.list_home)
        lv.layoutManager = LinearLayoutManager(requireContext())

       // var cont = getActivity().getApplicationContext()
        // lv.adapter =  UploadPhotoAdapter(data, context = context as Activity)

        getDataFromFirebase()

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        fun newInstance() = FragmentHome()
    }

    fun getDataFromFirebase() {

        val newReference = firebaseDatabase!!.getReference("Posts")

        newReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                //println(p0)
                //println("children: " + p0!!.children)
                //println("key:" + p0!!.key)
                //println("value:" + p0!!.value)

                postAdapter!!.clear()
                data.clear()

                for (snapshot in p0.children) {
                    val hashMap = snapshot.value as HashMap<String, String>
                    if (hashMap.size > 0) {
                        val photoPost = Photo(hashMap["userEmail"], hashMap["imageUrl"])
                        data.add(photoPost)
                        postAdapter!!.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }



}