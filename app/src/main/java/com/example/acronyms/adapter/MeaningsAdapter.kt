package com.example.acronyms.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.MeaningsItemClickListener
import com.example.acronyms.databinding.MeaningsItemBinding
import kotlinx.android.synthetic.main.meanings_item.view.*

/**
 * This is MeaningsAdapter class to display list of large forms in recyclerview.
 */
class MeaningsAdapter  constructor(private val listener: MeaningsItemClickListener) : RecyclerView.Adapter<MainViewHolder>(){

    private var acronymList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MeaningsItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAcronymList(lfs: List<String>) {
        this.acronymList = lfs.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return acronymList.size
    }



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val fullForm = acronymList[position]
        holder.binding.acronymTv.text = fullForm
        holder.itemView.setOnClickListener {
             listener.onItemClick(fullForm)
        }
    }
}

class MainViewHolder (val binding:MeaningsItemBinding) : RecyclerView.ViewHolder(binding.root){

}


