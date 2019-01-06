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
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.adapter.PhotoAdapter
import ufrpe.mobile.instantstore.adapter.UploadPhotoAdapter
import ufrpe.mobile.instantstore.model.Photo

class FragmentHome : Fragment() {

    private val TAG = "FragmentHome"
    private var mAdapter: PhotoAdapter? = null
    private var firestoreDB: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile, container, false)

        firestoreDB = FirebaseFirestore.getInstance()

        loadNotesList()

        /*firestoreListener = firestoreDB!!.collection("Post")
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e(TAG, "Listen failed!", e)
                    return@EventListener
                }

                val notesList = mutableListOf<Photo>()

                for (doc in documentSnapshots!!) {
                    val note = doc.toObject(Photo::class.java)
                    note.id = doc.id
                    notesList.add(note)
                }

                mAdapter = PhotoAdapter(notesList, requireContext(), firestoreDB!!)
                list_home.adapter = mAdapter
            })*/

        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        firestoreListener!!.remove()
    }

    private fun loadNotesList() {
        firestoreDB!!.collection("Post")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val notesList = mutableListOf<Photo>()

                    for (doc in task.result!!) {
                        val note = doc.toObject<Photo>(Photo::class.java)
                        note.id = doc.id
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