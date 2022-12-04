package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemSimilarBinding
import com.bluerayws.avit.dataclass.TestClass

class SimilarItemAdapter(val list: ArrayList<TestClass>) :
    RecyclerView.Adapter<SimilarItemAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.productName.text = data.name
        holder.binding.productPrice.text = "1 JD"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class Holder(val binding: ItemSimilarBinding) : RecyclerView.ViewHolder(binding.root)
}