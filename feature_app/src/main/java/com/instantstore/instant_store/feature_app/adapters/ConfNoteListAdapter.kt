package com.instantstore.instant_store.feature_app.adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instantstore.instant_store.feature_app.R
import kotlinx.android.synthetic.main.conf_node.view.*

class ConfNoteListAdapter(private val notes: List<Note> ,
private val context: Context
) : Adapter<ConfNoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.conf_node, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val note = notes[p1]
        p0?.let {
            p0.title.text = note.title
            p0.description.text = note.description
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.conf_node_title
        val description = itemView.note_item_description
        fun bindView(note: Note) {
            title.text = note.title
            description.text = note.description
        }
    }
    class Note(val title: String, val description: String)
}

