package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemMyOrderBinding
import com.bluerayws.avit.dataclass.TestClass

class MyOrderAdapter(
    val list: ArrayList<TestClass>,
    val onItemClick: MyOrder,
) : RecyclerView.Adapter<MyOrderAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMyOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val adapter = MyOrderChildAdapter()
        holder.binding.rvChiled.adapter = adapter
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(val binding: ItemMyOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewDetail = binding.viewDetail

        init {
            viewDetail.setOnClickListener {
                onItemClick.onItemClicked()
            }
        }
    }

    interface MyOrder {
        fun onItemClicked()
    }
}