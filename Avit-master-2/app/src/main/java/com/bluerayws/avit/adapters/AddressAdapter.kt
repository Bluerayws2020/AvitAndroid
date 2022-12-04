package com.bluerayws.avit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemAddressBinding
import com.bluerayws.avit.dataclass.CustomerData
import com.bluerayws.avit.dataclass.LocationsData

class AddressAdapter(val list: List<LocationsData>): //,private val listener: AddressClicked) :
    RecyclerView.Adapter<AddressAdapter.Holder>() {

    private var clickedState = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]

        holder.binding.tvAddress.text = data.address.toString()


        //check current address
        when (data.phone) {
                0 -> {
                    holder.binding.circleBlack.visibility = View.GONE
                    holder.binding.circleWhite.visibility = View.VISIBLE
                }
                else -> {
                    holder.binding.circleBlack.visibility = View.VISIBLE
                    holder.binding.circleWhite.visibility = View.GONE

                }
            }
        //item Checked
        holder.binding.root.setOnClickListener {
            holder.binding.circleBlack.visibility = View.VISIBLE
            holder.binding.circleWhite.visibility = View.GONE
            if (clickedState != holder.adapterPosition) {
                notifyItemChanged(clickedState)
                clickedState = holder.adapterPosition
            }
        }
        //item not checked
        let {
            when (clickedState) {
                -1 -> {
                    holder.binding.circleBlack.visibility = View.GONE
                    holder.binding.circleWhite.visibility = View.VISIBLE

                }
                holder.adapterPosition -> {
                    holder.binding.circleBlack.visibility = View.VISIBLE
                    holder.binding.circleWhite.visibility = View.GONE


                }
                else -> {
                    holder.binding.circleBlack.visibility = View.GONE
                    holder.binding.circleWhite.visibility = View.VISIBLE

                }
            }
        }

//        holder.binding.editAddress.setOnClickListener {
//            listener.onClick(position)
//        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root)
}