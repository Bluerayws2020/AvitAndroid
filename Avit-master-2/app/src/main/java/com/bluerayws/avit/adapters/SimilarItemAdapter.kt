package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemSimilarBinding
import com.bluerayws.avit.dataclass.RelatedProductsItems
import com.bluerayws.avit.dataclass.TestClass
import com.bumptech.glide.Glide

class SimilarItemAdapter(private val list: List<RelatedProductsItems>, private val context: Context) :
    RecyclerView.Adapter<SimilarItemAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.productName.text = data.name_ar

        Glide.with(context)
            .load(data.image)
            .placeholder(R.drawable.img_artboard68)
            .error(R.drawable.img_artboard68)
            .into(holder.binding.imageView4)


        holder.binding.productPrice.text = data.description_en
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