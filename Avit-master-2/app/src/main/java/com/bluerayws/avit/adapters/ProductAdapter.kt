package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemFavoriteBinding
import com.bluerayws.avit.dataclass.TestClass
import com.bluerayws.avit.dataclass.customer_wishlist

class ProductAdapter(val list: ArrayList<TestClass>, private val click: ItemClicked) :  // TestClass to customer Wishlist
    RecyclerView.Adapter<ProductAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.productName.text = data.name

        holder.binding.root.setOnClickListener {
            click.onItemClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}