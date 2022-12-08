package com.bluerayws.avit.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.databinding.ItemColorBinding
import com.bluerayws.avit.dataclass.Colors


class ColorAdapter(private val list: List<Colors>, private val context: Context, private val clickListener: AddressClicked) : RecyclerView.Adapter<ColorAdapter.Holder>() {

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
        val preferences =
            context.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

        holder.binding.tvColor.setOnClickListener {
            holder.binding.ivChecked.visibility = View.VISIBLE

            clickListener.onClick(position)

            // color id
//            Log.d("Color Id: ", "onBindViewHolder: " + data.id)
//
//            preferences.edit().apply {
//                putString("color_id", data.id)
//            }.apply()


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