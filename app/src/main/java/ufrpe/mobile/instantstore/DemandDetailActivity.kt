package ufrpe.mobile.instantstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_demand_detail.*

class DemandDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demand_detail)

        loadInformationsInScreen()
    }


    fun loadInformationsInScreen() {

        val img = intent.getStringExtra("imgUrl")

        Picasso
            .with(applicationContext)
            .load(img)
            .into(img_demandNode)

        val clientD = intent.getStringExtra("userClient")
        val NumberD = intent.getStringExtra("phonenumber")
        val textD = intent.getStringExtra("text")


        tv_EntryEmail.text = clientD
        tv_EntryNumber.text = NumberD
        tv_comentDemand.text = textD

    }


    fun DemandDetailBacktoMain(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)
    }
}
