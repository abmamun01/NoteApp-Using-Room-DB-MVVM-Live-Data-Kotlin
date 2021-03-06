package com.mamunsproject.notetakingappkotlin.Ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.ViewModel.NotesViewModel
import com.mamunsproject.notetakingappkotlin.databinding.FragmentCreateNoteBinding
import java.util.*


class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false)


        //    binding.prGreen.setImageResource(R.drawable.ic_baseline_done_24)


        binding.btnSave.setOnClickListener { createNotes(it) }

        binding.prGreen.setOnClickListener {
            binding.prGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.prYellow.setImageResource(0)
            binding.prRed.setImageResource(0)
            priority = "1"
        }
        binding.prRed.setOnClickListener {
            binding.prRed.setImageResource(R.drawable.ic_baseline_done_24)

            binding.prYellow.setImageResource(0)
            binding.prGreen.setImageResource(0)
            priority = "2"

        }
        binding.prYellow.setOnClickListener {
            binding.prYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.prRed.setImageResource(0)
            binding.prGreen.setImageResource(0)
            priority = "3"

        }




        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()


        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)


        val data = Notes_Entity(
            null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)
        Toast.makeText(context, "Notes Created Successfully!", Toast.LENGTH_SHORT).show()

        //Going home fragment after added note
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)

    }


}