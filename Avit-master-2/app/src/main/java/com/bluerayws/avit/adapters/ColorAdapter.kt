package com.bluerayws.avit.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemColorBinding
import com.bluerayws.avit.dataclass.colors_main


class ColorAdapter(val list: ArrayList<colors_main>) : RecyclerView.Adapter<ColorAdapter.Holder>() {

    private var clickedState = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.adapterPosition
        holder.binding.tvColor.text = data.color_name_ar

//        holder.binding.tvColor.backgroundTintList =
//            holder.binding.tvSize.text = data.size_name_ar

//        ColorStateList.valueOf(Color.parseColor(data.col_id))
        //item Checked

        holder.binding.tvColor.setOnClickListener {
            holder.binding.ivChecked.visibility = View.VISIBLE

            if (clickedState != holder.adapterPosition) {
                notifyItemChanged(clickedState)
                clickedState = holder.adapterPosition
            }
        }
        //item not checked

        when (clickedState) {
            -1 -> {
                holder.binding.ivChecked.visibility = View.GONE
            }
            holder.adapterPosition -> {
                holder.binding.ivChecked.visibility = View.VISIBLE

            }
            else -> {
                holder.binding.ivChecked.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root)
}