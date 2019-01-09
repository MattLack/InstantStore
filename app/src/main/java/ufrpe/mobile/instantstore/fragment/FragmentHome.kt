package ufrpe.mobile.instantstore.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.android.synthetic.main.fragment_view_profile.*
import ufrpe.mobile.instantstore.MainScreenActivity
import com.google.firebase.firestore.CollectionReference;
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.adapter.PhotoAdapter
import ufrpe.mobile.instantstore.model.Photo


class FragmentHome : Fragment() {

    private val TAG = "FragmentHome"
    private var mAdapter: PhotoAdapter? = null
    private var firestoreDB: FirebaseFirestore? = null
    val notesList = mutableListOf<Photo>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile, container, false)

        firestoreDB = FirebaseFirestore.getInstance()


        loadNotesList()
        //readData()

        return view

    }

    private fun loadNotesList() {
        firestoreDB!!.collection("Post")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {


                    for (doc in task.result!!) {

                        val t = doc.data as HashMap<String, String>
                        val note = Photo(t["imgUrl"], t["txt"], t["userEmail"], t["id"])
                        notesList.add(note)
                    }

                    mAdapter = PhotoAdapter(notesList, requireContext(), firestoreDB!!)
                    val mLayoutManager = LinearLayoutManager(requireContext())
                    list_home.layoutManager = mLayoutManager
                    list_home.itemAnimator = DefaultItemAnimator()
                    list_home.adapter = mAdapter
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }


    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun newInstance() = FragmentHome()
    }

}