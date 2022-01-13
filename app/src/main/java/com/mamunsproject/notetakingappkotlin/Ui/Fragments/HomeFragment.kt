package com.mamunsproject.notetakingappkotlin.Ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.Ui.Adapter.NotesAdapter
import com.mamunsproject.notetakingappkotlin.ViewModel.NotesViewModel
import com.mamunsproject.notetakingappkotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes_Entity>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcAllNote.layoutManager = staggeredGridLayoutManager


        //Get all Notes
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->

            oldMyNotes = notesList as ArrayList<Notes_Entity>

            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcAllNote.adapter = adapter


        })




        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }


        clickListener()




        return binding.root
    }

    private fun clickListener() {

        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes_Entity>

                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcAllNote.adapter = adapter
            })

        }
        binding.filterMedium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes_Entity>

                binding.rcAllNote.adapter = NotesAdapter(requireContext(), notesList)

            })

        }
        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes_Entity>

                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcAllNote.adapter = adapter
            })

        }

        binding.allNotes.setOnClickListener {

            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes_Entity>

                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcAllNote.adapter = adapter
            })

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.appbar_search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                notesFiltering(newText)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(newText: String?) {

        //new list baniye adapter k dite hobe
        val newFilteredList = arrayListOf<Notes_Entity>()

        for (i in oldMyNotes) {
            if (i.title.contains(newText!!) || i.subtitle.contains(newText!!)) {
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)
    }


}