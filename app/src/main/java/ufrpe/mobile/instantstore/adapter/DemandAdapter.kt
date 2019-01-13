package ufrpe.mobile.instantstore.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ufrpe.mobile.instantstore.DemandDetailActivity
import ufrpe.mobile.instantstore.R
import ufrpe.mobile.instantstore.model.Demand

class DemandAdapter(
    private val demandList: MutableList<Demand>,
    private val context: Context
) : RecyclerView.Adapter<DemandAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.photo_node, p0, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val note = demandList[p1]

        //p0.title.text = note.author
       // p0.content.text = note.txt
        Picasso
            .with(context)
            .load(demandList[p1].img)
            .into(p0.image)

        p0.itemView.setOnClickListener {
            val intent = Intent(context, DemandDetailActivity::class.java)
            intent.putExtra("imgUrl", demandList[p1].img)
            //intent.putExtra("author", demandList[p1].author)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return demandList.size
    }


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var content: TextView
        internal var image: ImageView

        init {
            title = view.findViewById(R.id.tv_authorUser)
            content = view.findViewById(R.id.tv_comment)
            image = view.findViewById(R.id.imgv_posted)

        }
    }
}