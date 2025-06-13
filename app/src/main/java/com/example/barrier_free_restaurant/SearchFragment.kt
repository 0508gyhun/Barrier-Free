package com.example.barrier_free_restaurant

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.barrier_free_restaurant.databinding.FragmentSearchBinding
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar = binding.searchBar
        val searchView = binding.searchView
        setSearchBar(searchBar, searchView)
        setSearchView(searchView, searchBar)
    }

    private fun setSearchView(
        searchView: SearchView,
        searchBar: SearchBar
    ) {
        searchView
            .editText
            .setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
                searchBar.setText(searchView.text)
                searchView.hide()
                false
            }
    }

    private fun setSearchBar(
        searchBar: SearchBar,
        searchView: SearchView
    ) {
        searchBar.setOnClickListener {
            searchView.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}