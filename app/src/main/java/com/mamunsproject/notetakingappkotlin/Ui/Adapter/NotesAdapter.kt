package com.mamunsproject.notetakingappkotlin.Ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity
import com.mamunsproject.notetakingappkotlin.R
import com.mamunsproject.notetakingappkotlin.Ui.Fragments.HomeFragmentDirections
import com.mamunsproject.notetakingappkotlin.databinding.ItemNotesBinding

class NotesAdapter(val requireContext: Context, val notesList: List<Notes_Entity>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {


    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {

        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val data = notesList[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubtitle.text = data.subtitle.toString()
        holder.binding.notesDate.text = data.date.toString()


        //Set priority
        when (data.priority) {

            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yello_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }


        }


        holder.binding.root.setOnClickListener {

            //Navigation graphe arg parse korclilam tai akhane constructor chacce
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount(): Int {

        return notesList.size
    }
}