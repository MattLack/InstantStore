package com.instantstore.instant_store.feature_app.adapters


import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup

class ConfNoteListAdapter(private val notes: List<Note>) : Adapter<ConfNoteListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    class Note(val title: String, val description: String)
}

