package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemMyBagBinding
import com.bluerayws.avit.dataclass.TestClass

class BagAdapter(
    val list: ArrayList<TestClass>,
    val context: Context,
) : RecyclerView.Adapter<BagAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMyBagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.textView10.text = data.name
        holder.binding.root.setOnClickListener {
            Toast.makeText(context, "" + data.phone, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemMyBagBinding) : RecyclerView.ViewHolder(binding.root)
}