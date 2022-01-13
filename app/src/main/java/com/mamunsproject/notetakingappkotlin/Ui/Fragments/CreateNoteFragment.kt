package com.mamunsproject.notetakingappkotlin.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.databinding.FragmentCreateNoteBinding


class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false)




        return binding.root
    }


}