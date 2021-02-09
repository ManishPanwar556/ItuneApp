package com.example.itunesapp.ui

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itunesapp.R
import com.example.itunesapp.databinding.ActivityMainBinding
import com.example.itunesapp.viewModel.ItunesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:ItunesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val rev=binding.rev
        val progressBar=binding.progressBar
        val textView=binding.textView
        val adapter=MainRecyclerAdapter()
        rev.adapter=adapter
        viewModel.artistList.observe(this, Observer {
            if (it.isNotEmpty()) {
                progressBar.visibility = View.GONE
                textView.visibility = View.GONE
                binding.error.visibility=View.GONE
                adapter.setData(it)
                rev.layoutManager = GridLayoutManager(this, 3)
            }
            else{
                progressBar.visibility=View.GONE
                textView.visibility = View.GONE
                binding.error.visibility=View.VISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val searchItem=menu?.findItem(R.id.actionSearch)
        val searchView=searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.progressBar.visibility= View.VISIBLE
                binding.textView.visibility=View.GONE
                val searchTerm=query?.capitalizeWords()
                searchTerm?.let{
                    viewModel.searchArtist(searchTerm)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?)=false

        })
        return true
    }
    fun String.capitalizeWords()= split(" ").map { it.toLowerCase().capitalize() }.joinToString(" ")
}