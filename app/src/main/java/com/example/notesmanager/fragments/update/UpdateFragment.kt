package com.example.notesmanager.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesmanager.R
import com.example.notesmanager.model.Note
import com.example.notesmanager.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.findViewById<EditText>(R.id.updateTitle).setText(args.currentNote.title)
        view.findViewById<EditText>(R.id.updateDescription).setText(args.currentNote.description)

        view.findViewById<Button>(R.id.update_button).setOnClickListener{
            updateItem()
        }

        // Remove menu for UpdateFragment since we're handling it in MainActivity
        setHasOptionsMenu(false)

        return view
    }

    private fun updateItem(){
        val title = view?.findViewById<EditText>(R.id.updateTitle)?.text.toString().trim()
        val description = view?.findViewById<EditText>(R.id.updateDescription)?.text.toString().trim()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.format(Date())

        if(inputCheck(title, description)){
            // Create user object
            val updateNote = Note(args.currentNote.id, title, description, date)
            // update data to database
            mNoteViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Note updated Successfully!", Toast.LENGTH_LONG).show()
            // navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck( title: String, description: String ): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    /*
       override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
           inflater.inflate(R.menu.delete_menu, menu)
       }

       override fun onOptionsItemSelected(item: MenuItem): Boolean {
           if(item.itemId == R.id.menu_delete){
               deleteNote()
           }
           return super.onOptionsItemSelected(item)
       }
   */

    fun deleteNote(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), "Successfully removed.", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title}?")
        builder.create().show()
    }
}