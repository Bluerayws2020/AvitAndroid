package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemChiledBinding

class MyOrderChildAdapter : RecyclerView.Adapter<MyOrderChildAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemChiledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 15
    }

    class Holder(val binding: ItemChiledBinding) : RecyclerView.ViewHolder(binding.root)
}