package com.example.gb_translator.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_translator.databinding.ActivityMainRvItemBinding
import com.example.model.entity.Word
import com.example.repository.utils.getTranslationsString

class MainAdapter(private var itemClickListener: ((Word) -> Unit)?) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<Word> = arrayListOf()

    fun setData(data: List<Word>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainRvItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: ActivityMainRvItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private lateinit var data: Word

        init {
            itemView.setOnClickListener { itemClickListener?.invoke(data) }
        }

        fun bind(word: Word) {
            this.data = word
            vb.headerTextviewRv.text = word.text
            vb.descriptionTextviewRv.text = word.getTranslationsString()
            vb.transcriptionTextviewRv.text = word.translations?.get(0)?.transcription
        }
    }
}