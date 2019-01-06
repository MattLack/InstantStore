package ufrpe.mobile.instantstore.fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.activity_upload.view.*
import ufrpe.mobile.instantstore.MainScreenActivity
import ufrpe.mobile.instantstore.R
import java.io.IOException
import java.util.*

class FragmentUpload : Fragment() {

    var resolver = activity!!.contentResolver
    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri? = null
    lateinit var db: FirebaseFirestore
    lateinit var notesCollectionRef: CollectionReference
    var mAuth: FirebaseAuth? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_upload, container, false)

        mAuth = FirebaseAuth.getInstance()

        //firestore

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance()

        // Reference to a Collection
        notesCollectionRef = db.collection("InstantStore")

        //buttons
        view.img_uploaded.setOnClickListener {
            choseImageFragment()
        }

        view.btn_uploadimage.setOnClickListener {
            uploadImageFragment()
        }

        view.btn_return.setOnClickListener {
            backScreenFragment()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //set imageview after user select image
        if (requestCode == PICK_IMAGE_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null && data.data != null
        ) {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(resolver, filePath)
                img_uploaded.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun choseImageFragment() {
        showFileChooser()
    }

    private fun uploadImageFragment() {
        val userComment = textComment.text.toString()
        uploadImagetoFirebase(userComment)
    }

    private fun uploadImagetoFirebase(coment: String) {

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        ref.putFile(filePath!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    saveAllInformations(it.toString(), coment)
                    progressDialog.dismiss()
                }
            }
            .addOnProgressListener { taskSnapShot ->
                val progress = (100.0 * taskSnapShot.bytesTransferred) / taskSnapShot.totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    img_uploaded.setImageResource(R.drawable.ic_upload)
                    textComment.setText("")
                }
            }
    }

    private fun saveAllInformations(imgurl: String, comentary: String) {
        val user = mAuth!!.currentUser
        val userEmail = user!!.email.toString()
        val uid = UUID.randomUUID().toString()
        val uploadMap = HashMap<String, Any>()

        uploadMap["userEmail"] = userEmail
        uploadMap["txt"] = comentary
        uploadMap["imgUrl"] = imgurl
        uploadMap["id"] = uid

        db.collection("Post").add(uploadMap).addOnSuccessListener {
            Toast.makeText(context, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(context, "deu ruim", Toast.LENGTH_LONG).show()
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }


    fun backScreenFragment() {
        val intent = Intent(context, MainScreenActivity::class.java)
        startActivity(intent)
    }


}
