package ufrpe.mobile.instantstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_formulary.*
import java.util.*

class FormularyActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    lateinit var notesCollectionRef: CollectionReference
    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulary)

        mAuth = FirebaseAuth.getInstance()

        //firestore

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance()

        // Reference to a Collection
        notesCollectionRef = db.collection("InstantStore")

        loadInformationsInScreen()

    }

    fun SubmitFormulary(view: View) {

        val img = intent.getStringExtra("imgUrl")
        val authorD = intent.getStringExtra("author")
        val coment = text_form.text.toString()

        saveAllInformations(img, coment, authorD)

        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)

    }

    fun loadInformationsInScreen() {

        val img = intent.getStringExtra("imgUrl")

        Picasso
            .with(applicationContext)
            .load(img)
            .into(img_photoform)

        val authorD = intent.getStringExtra("author")

        tv_author.text = authorD

    }


    fun FormToBackScreen(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)
    }

    private fun saveAllInformations(imgurl: String, comentary: String, authorForPost: String) {
        val user = mAuth!!.currentUser
        val userEmail = user!!.email.toString()
        val uid = UUID.randomUUID().toString()
        val uploadMap = HashMap<String, Any>()

        uploadMap["userClient"] = userEmail
        uploadMap["txt"] = comentary
        uploadMap["imgUrl"] = imgurl
        uploadMap["id"] = uid
        uploadMap["userStore"] = authorForPost
        uploadMap["phoneNumber"] = txt_phoneform.text.toString()

        db.collection("Demand").add(uploadMap).addOnSuccessListener {
            Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Upload fail :(", Toast.LENGTH_LONG).show()
        }
    }


}
