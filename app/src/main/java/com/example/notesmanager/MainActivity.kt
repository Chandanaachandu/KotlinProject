package com.example.notesmanager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesmanager.databinding.ActivityMainBinding
import com.example.notesmanager.fragments.list.ListFragment

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private var currentSearchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Listen for navigation changes to update toolbar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listFragment -> {
                    // Show search icon for ListFragment
                    invalidateOptionsMenu()
                }
                R.id.updateFragment -> {
                    // Show delete icon for UpdateFragment
                    invalidateOptionsMenu()
                }
                else -> {
                    // Hide both icons for other fragments (AddFragment)
                    invalidateOptionsMenu()
                }
            }
        }

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuInflater.inflate(R.menu.delete_menu, menu)

        // Get current fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as? NavHostFragment
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

        when (currentFragment) {
            is ListFragment -> {
                // Show only search icon, hide delete icon
                menu?.findItem(R.id.menu_search)?.isVisible = true
                menu?.findItem(R.id.menu_delete)?.isVisible = false

                // Setup search functionality
                val search = menu?.findItem(R.id.menu_search)
                val searchView = search?.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this)
                currentSearchView = searchView
            }
            is com.example.notesmanager.fragments.update.UpdateFragment -> {
                // Show only delete icon, hide search icon
                menu?.findItem(R.id.menu_search)?.isVisible = false
                menu?.findItem(R.id.menu_delete)?.isVisible = true
            }
            else -> {
                // Hide both icons for AddFragment and others
                menu?.findItem(R.id.menu_search)?.isVisible = false
                menu?.findItem(R.id.menu_delete)?.isVisible = false
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                // Handle delete in UpdateFragment
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as? NavHostFragment
                val currentFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

                if (currentFragment is com.example.notesmanager.fragments.update.UpdateFragment) {
                    currentFragment.deleteNote()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchDatabase(it)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            searchDatabase(it)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as? NavHostFragment
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

        if (currentFragment is ListFragment) {
            currentFragment.searchNotes(searchQuery)
        }
    }
}