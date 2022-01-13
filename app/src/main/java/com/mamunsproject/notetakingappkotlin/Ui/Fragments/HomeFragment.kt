package com.mamunsproject.notetakingappkotlin.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.Ui.Adapter.NotesAdapter
import com.mamunsproject.notetakingappkotlin.ViewModel.NotesViewModel
import com.mamunsproject.notetakingappkotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)



        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->

            binding.rcAllNote.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcAllNote.adapter = NotesAdapter(requireContext(), notesList)

        })

        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }






        return binding.root
    }


}