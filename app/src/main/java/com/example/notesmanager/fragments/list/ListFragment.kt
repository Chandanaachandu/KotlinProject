package com.example.notesmanager.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesmanager.R
import com.example.notesmanager.viewModel.NoteViewModel
import com.example.notesmanager.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview - using View Binding
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // NoteViewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Observe all data by default
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)
    }

    // Function to handle search from MainActivity
    fun searchNotes(searchQuery: String) {
        if (searchQuery == "%%") {
            // If search is empty, show all notes
            mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
                adapter.setData(note)
            })
        } else {
            // Perform search
            mNoteViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner, Observer { notes ->
                adapter.setData(notes)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(), "Successfully removed everything.", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }
    */

}