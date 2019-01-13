package ufrpe.mobile.instantstore.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.conf_node.view.*
import ufrpe.mobile.instantstore.MainActivity
import ufrpe.mobile.instantstore.R

class FragmentSettings : Fragment() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.conf_node, container, false)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { }

        view.note_item_description.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        mAuth!!.signOut()
        val intent3 = Intent(context, MainActivity::class.java)
        startActivity(intent3)
    }

}

