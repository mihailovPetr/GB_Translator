package com.example.historyscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_translator.databinding.FragmentHistoryRvItemBinding
import com.example.repository.entity.room.HistoryEntity

class HistoryAdapter(private var itemClickListener: ((HistoryEntity) -> Unit)? = null) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<HistoryEntity> = arrayListOf()

    fun setData(data: List<HistoryEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            FragmentHistoryRvItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: FragmentHistoryRvItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private lateinit var data: HistoryEntity

        init {
            itemView.setOnClickListener { itemClickListener?.invoke(data) }
        }

        fun bind(data: HistoryEntity) {
            this.data = data
            vb.headerHistoryTextviewRecyclerItem.text = data.word
        }
    }
}