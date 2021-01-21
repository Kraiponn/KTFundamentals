package com.codemakerlab.ktfundamentals.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemakerlab.ktfundamentals.R
import com.codemakerlab.ktfundamentals.model.Note

class NoteAdapter(var noteList: List<Note>, var onItemClick: IOnNoteClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_note_item,
            parent,
            false
        )

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var curNoteItem = noteList[position]

        holder.binding(curNoteItem, onItemClick)
    }

    override fun getItemCount(): Int = noteList.size

    class NoteViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById<TextView>(R.id.text_view_note_title)
        var icon: ImageView = itemView.findViewById<ImageView>(R.id.image_view_note_logo)

        fun binding(note: Note, itemAction: IOnNoteClickListener) {
            tvTitle.text = note.title
            icon.setImageResource(note.icon)

            itemView.setOnClickListener {
                itemAction.onClick(adapterPosition)
            }
        }
    }
}

interface IOnNoteClickListener {
    fun onClick(position: Int)
}