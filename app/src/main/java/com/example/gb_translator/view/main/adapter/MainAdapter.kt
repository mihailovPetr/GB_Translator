package com.example.gb_translator.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_translator.databinding.ActivityMainItemBinding
import com.example.gb_translator.model.data.DataModel

class MainAdapter(private var data: List<DataModel>) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: ActivityMainItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(data: DataModel) {
            vb.headerTextviewRv.text = data.text
            vb.descriptionTextviewRv.text = data.meanings?.get(0)?.translation?.translation
            vb.transcriptionTextviewRv.text = data.meanings?.get(0)?.transcription
        }
    }
}