package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemLocationBinding
import com.bluerayws.avit.dataclass.StoreDetails
import com.bluerayws.avit.dataclass.TestClass2

class LocationAdapter(
    val list: List<StoreDetails>, val context: Context,
    private val clicked: LocationClicked
) : RecyclerView.Adapter<LocationAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.locatinTv.text = data.name_en
        holder.binding.roadTv.text = data.address_en
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class Holder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {
        private val callBtn = binding.ivCall
        private val navigateBtn = binding.navigateButton

        init {
            callBtn.setOnClickListener {
                val data = list[adapterPosition]
                clicked.onCallClicked(data.phone)
            }
            navigateBtn.setOnClickListener {
                val data = list[adapterPosition]
                clicked.onDirectionClicked(data.url)
            }
        }
    }


    interface LocationClicked {
        fun onCallClicked(data: String)
        fun onDirectionClicked(data: String)
    }
}
