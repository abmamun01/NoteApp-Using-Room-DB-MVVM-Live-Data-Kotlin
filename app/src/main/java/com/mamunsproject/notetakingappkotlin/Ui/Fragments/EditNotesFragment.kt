package com.mamunsproject.notetakingappkotlin.Ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.ViewModel.NotesViewModel
import com.mamunsproject.notetakingappkotlin.databinding.FragmentEditNotesBinding
import java.util.*


class EditNotesFragment : Fragment() {


    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        binding.editTitle.setText(oldNotes.customParse.title)
        binding.editSubtitle.setText(oldNotes.customParse.subtitle)
        binding.editNotes.setText(oldNotes.customParse.notes)


        //Set priority
        when (oldNotes.customParse.priority) {

            "1" -> {

                priority = "1"
                binding.prGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.prYellow.setImageResource(0)
                binding.prRed.setImageResource(0)
            }
            "2" -> {

                priority = "2"
                binding.prRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.prYellow.setImageResource(0)
                binding.prGreen.setImageResource(0)
            }
            "3" -> {

                priority = "3"
                binding.prYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.prRed.setImageResource(0)
                binding.prGreen.setImageResource(0)
            }


        }

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


        binding.btnSave.setOnClickListener {
            updateNotes(it)
        }

        return binding.root

    }

    private fun updateNotes(it: View?) {

        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()


        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)


        val data = Notes_Entity(
            oldNotes.customParse.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(context, "Notes Updated Successfully!", Toast.LENGTH_SHORT).show()

        //Going home fragment after added note
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {

            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialuoge_delete)


            val textYes = bottomSheet.findViewById<TextView>(R.id.dialogue_yes)
            val textNo = bottomSheet.findViewById<TextView>(R.id.dialogue_no)

            textYes?.setOnClickListener {

                viewModel.deleteNotes(oldNotes.customParse.id!!)
                //Going home fragment after added note
             //   Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
                bottomSheet.dismiss()

            }
            textNo?.setOnClickListener {

                bottomSheet.dismiss()
            }


            bottomSheet.show()


        }
        return super.onOptionsItemSelected(item)
    }
}