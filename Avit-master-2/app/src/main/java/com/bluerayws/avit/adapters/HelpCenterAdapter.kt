package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemHelpCenterBinding
import com.bluerayws.avit.dataclass.HelpModel


class HelpCenterAdapter(var list: ArrayList<HelpModel>, private val listener: HelpCenterListener) :
        RecyclerView.Adapter<HelpCenterAdapter.Holder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding =
                ItemHelpCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = list[position]
            holder.binding.ivTitle.setImageResource(data.image)
            holder.binding.tvTitle.text = data.title

            holder.binding.root.setOnClickListener {
                listener.onClick(position)
            }

        }

        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(val binding: ItemHelpCenterBinding) : RecyclerView.ViewHolder(binding.root)



}