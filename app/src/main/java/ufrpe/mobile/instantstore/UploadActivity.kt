package ufrpe.mobile.instantstore

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.design.widget.TextInputEditText
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.activity_upload.view.*
import ufrpe.mobile.instantstore.R.drawable.ic_upload
import ufrpe.mobile.instantstore.fragment.FragmentHome
import ufrpe.mobile.instantstore.model.Photo
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class UploadActivity : AppCompatActivity() {


    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    var firebaseDatabase: FirebaseDatabase? = null
    lateinit var db: DocumentReference
    internal var storageReference: StorageReference? = null
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        //Init firebase
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.getReference("Posts")
        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        db = FirebaseFirestore.getInstance().document("Posts")


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

    private fun uploadImagetoFirebase(coment:String) {

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        ref.putFile(filePath!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    saveAllInformations(it.toString(),coment)
                    progressDialog.dismiss()
                    //Toast.makeText(this, "File Uploaded", Toast.LENGTH_SHORT).show()
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
                    //Toast.makeText(applicationContext, "Upload Completed!", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveAllInformations(imgurl: String,comentary:String) {
        val uid = FirebaseAuth.getInstance().uid
        val ref = firebaseDatabase!!.getReference("/Posts/$uid")

        val user = mAuth!!.currentUser
        val userEmail = user!!.email.toString()

        val photo = Photo(imgurl, comentary, userEmail)

        ref.setValue(photo)
            .addOnSuccessListener {
                Toast.makeText(this, "Save in database", Toast.LENGTH_SHORT).show()
            }.addOnCompleteListener {
                Toast.makeText(this, "Save in database", Toast.LENGTH_SHORT).show()
            }
    }


    private fun uploadFile() {
        if (filePath != null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()


            val imageRef = storageReference!!.child("images/" + UUID.randomUUID().toString())
            imageRef.putFile(filePath!!)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "File Uploaded", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Filed", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapShot ->
                    val progress = (100.0 * taskSnapShot.bytesTransferred) / taskSnapShot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                }
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
                        img_uploaded.setImageResource(ic_upload)
                        textComment.setText("")
                        Toast.makeText(applicationContext, "Upload Completed!", Toast.LENGTH_LONG).show()
                    }

                }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }


/*






    var selected: Uri? = null
    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null
    var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase!!.reference
        mStorageRef = FirebaseStorage.getInstance().getReference("Posts")



    }


    fun upload(view: View) {

        val uuid = UUID.randomUUID()
        val imageName = "images/$uuid.jpg"
        val storageReference = mStorageRef!!.child(imageName)
        var uploadTask = storageReference.putFile(selected!!)
        val uuidString = uuid.toString()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        uploadTask.addOnSuccessListener { taskSnapshot ->

            val user = mAuth!!.currentUser
            val userEmail = user!!.email.toString()
            val userComment =  textcoment.text.toString()


            dbRef!!.child("Posts").child(uuidString).child("userEmail").setValue(userEmail)
            dbRef!!.child("Posts").child(uuidString).child("txt").setValue(userComment)

            storageReference.getDownloadUrl().addOnSuccessListener { uri ->
                dbRef!!.child("Posts").child(uuidString).child("imageUrl").setValue(uri.toString())
            }

        }

        uploadTask.addOnFailureListener { exception ->
            if (exception != null) {
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

        uploadTask.addOnCompleteListener { task ->
            if (task.isComplete) {
                img_uploaded.setImageResource(ic_upload)
                textcoment.setText("")
                Toast.makeText(applicationContext, "Upload Completed!", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun selectImage(view: View) {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 2)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selected = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selected)
                img_uploaded.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    */

    fun backScreen(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)
    }


}
