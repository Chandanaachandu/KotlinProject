package com.example.notesmanager.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmanager.model.Note
import com.example.notesmanager.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(private val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteId.text = note.id.toString()
            binding.tvNoteTitle.text = note.title
            binding.tvNoteDescription.text = note.description
            binding.tvNoteDate.text = note.date

            binding.rowLayout.setOnClickListener {
                // Navigate to update fragment with the note data
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(note)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}