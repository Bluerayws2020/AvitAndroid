package com.bluerayws.avit.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemSizeBinding
import com.bluerayws.avit.dataclass.sizes_main

class SizeAdapter(val list: ArrayList<sizes_main>) : RecyclerView.Adapter<SizeAdapter.Holder>() {

    private var clickedState = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.tvSize.text = data.size_name_ar
        let {
            holder.binding.tvSize.setOnClickListener({
                holder.binding.tvSize.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                holder.binding.tvSize.setTextColor(Color.WHITE)
                if (clickedState != holder.adapterPosition) {
                    notifyItemChanged(clickedState)
                    clickedState = holder.adapterPosition
                }
            })
        }
        //item not checked
        let {
            if (clickedState == -1) {
                holder.binding.tvSize.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                holder.binding.tvSize.setTextColor(Color.BLACK)

            } else if (clickedState == holder.adapterPosition) {
                holder.binding.tvSize.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                holder.binding.tvSize.setTextColor(Color.WHITE)
            } else {
                holder.binding.tvSize.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                holder.binding.tvSize.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root)
}