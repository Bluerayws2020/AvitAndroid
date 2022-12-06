package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemMyBagBinding
import com.bluerayws.avit.dataclass.CustomerCartData
import com.bluerayws.avit.dataclass.TestClass
import com.bumptech.glide.Glide

class BagAdapter(
    private val list: List<CustomerCartData>,
    private val context: Context,
    private val itemClicked: DeleteItemClicked
) : RecyclerView.Adapter<BagAdapter.Holder>() {

    private val language = "ar"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMyBagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.productPrice.text = data.itemPrice
        holder.binding.textView19.text = data.product_name_ar
        holder.binding.textView13.text = data.quantity
        holder.binding.textView14.text = data.color_name_ar
        holder.binding.textView10.text = data.size_name_ar

        Glide.with(context)
            .load(HelperUtils.BASE_URL + data.product_image)
            .placeholder(R.drawable.img_artboard68)
            .error(R.drawable.img_artboard68)
            .into(holder.binding.imageView41)


        holder.binding.root.setOnClickListener {
            Toast.makeText(context, "" + data.wishlist, Toast.LENGTH_SHORT).show()
        }

        holder.binding.deleteBtn.setOnClickListener {
            itemClicked.removeItem(position)
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemMyBagBinding) : RecyclerView.ViewHolder(binding.root)
}