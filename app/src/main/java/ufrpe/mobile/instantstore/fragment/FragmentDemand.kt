package ufrpe.mobile.instantstore.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_demand.*
import ufrpe.mobile.instantstore.MainScreenActivity
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.adapter.DemandAdapter
import ufrpe.mobile.instantstore.model.Demand


class FragmentDemand : Fragment() {

    private val TAG = "FragmentDemand"
    private var mAdapter: DemandAdapter? = null
    private var firestoreDB: FirebaseFirestore? = null
    val notesList = mutableListOf<Demand>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_demand, container, false)

        firestoreDB = FirebaseFirestore.getInstance()

        loadNotesList()

        return view

    }

    private fun loadNotesList() {
        firestoreDB!!.collection("Demand")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    for (doc in task.result!!) {

                        val t = doc.data as HashMap<String, String>
                        val note = Demand(t["imgUrl"], t["phoneNumber"], t["userClient"], t["txt"], t["userStore"], t["id"])
                        notesList.add(note)
                    }

                    mAdapter = DemandAdapter(notesList, requireContext())
                    val mLayoutManager = LinearLayoutManager(requireContext())
                    list_demand.layoutManager = mLayoutManager
                    list_demand.itemAnimator = DefaultItemAnimator()
                    list_demand.adapter = mAdapter
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun newInstance() = FragmentDemand()
    }
}
