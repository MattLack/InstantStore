package ufrpe.mobile.instantstore.fragment

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ufrpe.mobile.instantstore.R
import kotlinx.android.synthetic.main.fragment_upload.*

class FragmentUpload : Fragment(), View.OnClickListener {

    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri? = null

    internal var storage: FirebaseStorage?=null
    internal var storageReference:StorageReference?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_upload,container, false)

        //Init firebase
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        //Setup button
        btn_choseimage.setOnClickListener(this)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //set imageview after user select image
        if(requestCode == PICK_IMAGE_REQUEST &&
                resultCode == Activity.RESULT_OK &&
                data != null && data.data != null){

        }

    }

    override fun onClick(v: View?) {
        if(v === btn_choseimage)
            showFileChooser()
        else if(v == btn_uploadimage)
            uploadFile()
    }

    private fun uploadFile() {
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"),PICK_IMAGE_REQUEST)
    }

}