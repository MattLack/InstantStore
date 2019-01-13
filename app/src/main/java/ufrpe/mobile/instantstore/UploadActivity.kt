package ufrpe.mobile.instantstore

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload.*
import ufrpe.mobile.instantstore.R.drawable.ic_upload
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class UploadActivity : AppCompatActivity() {


    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri? = null
    lateinit var db: FirebaseFirestore
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        mAuth = FirebaseAuth.getInstance()

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance()

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
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                img_uploaded.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    fun choseImage(v: View?) {
        showFileChooser()
    }

    fun uploadImage(v: View?) {
        val userComment = textComment.text.toString()
        uploadImagetoFirebase(userComment)
    }

    private fun uploadImagetoFirebase(coment: String) {

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        val progressDialog = ProgressDialog(this)
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
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    img_uploaded.setImageResource(ic_upload)
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
            Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Upload fail :(", Toast.LENGTH_LONG).show()
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }


    fun backScreen(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)
    }


}