package com.example.notesmanager.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesmanager.R
import com.example.notesmanager.model.Note
import com.example.notesmanager.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        view.findViewById<Button>(R.id.save_button).setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val title = view?.findViewById<EditText>(R.id.editTextTitle)?.text.toString().trim()
        val description = view?.findViewById<EditText>(R.id.editTextDescription)?.text.toString().trim()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.format(Date())

        if(inputCheck(title, description)){
            // Create user object
            val note = Note(0, title, description, date)
            // add data to database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Note added Successfully!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck( title: String, description: String ): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}