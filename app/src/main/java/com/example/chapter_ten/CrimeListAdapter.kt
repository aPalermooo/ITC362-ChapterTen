package com.example.chapter_ten

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter_ten.databinding.ListItemCrimeBinding
import com.example.chapter_ten.databinding.ListItemIntenseCrimeBinding

class CrimeHolder( private val binding : ListItemCrimeBinding ) : RecyclerView.ViewHolder(binding.root) {
    fun bind (crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText( binding.root.context, "${crime.title} click", Toast.LENGTH_SHORT).show()
        }
    }
}

class IntenseCrimeHolder ( private val binding : ListItemIntenseCrimeBinding ) : RecyclerView.ViewHolder(binding.root) {
    fun bind (crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.tattleButton.setOnClickListener {
            Toast.makeText( binding.root.context, "You're a coward!!", Toast.LENGTH_LONG).show()
        }
    }
}

class CrimeListAdapter ( private  val crimes : List<Crime> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == 1) {
            val binding = ListItemIntenseCrimeBinding.inflate(inflater, parent, false)
            return IntenseCrimeHolder(binding)
        } else {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return CrimeHolder(binding)
        }
    }

    override fun getItemCount(): Int = crimes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        if (getItemViewType(position) == 1) {
            (holder as IntenseCrimeHolder).bind(crime)
        }
        else {
            (holder as CrimeHolder).bind(crime)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]

        return when (crime.requiresPolice) {
            true -> IS_INTENSE_CRIME
            else -> IS_NORMAL_CRIME
        }
    }

    companion object {
        const val IS_INTENSE_CRIME = 1
        const val IS_NORMAL_CRIME = 0
    }
}