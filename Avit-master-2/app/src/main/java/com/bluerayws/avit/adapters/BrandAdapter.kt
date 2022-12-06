package com.bluerayws.avit.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemHomeCategoryBinding
import com.bluerayws.avit.dataclass.Brands
import com.bluerayws.avit.dataclass.Categories

class BrandAdapter(private val list: List<Brands>, private val context: Context,
                   val onclickListener: (Brands) -> Unit
) : RecyclerView.Adapter<BrandAdapter.Holder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

            val binding =
                ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)

        }


        override fun onBindViewHolder(holder: BrandAdapter.Holder, position: Int) {


            holder.binding.radioCategory.text = list[position].name_ar

            if (list[position].isSelected){
                holder.binding.radioCategory.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                holder.binding.radioCategory.setTextColor(context.getColor(R.color.white))
                Log.d("Color Category Adapter", "onBindViewHolder: Color is black" + list[position].isSelected)
                list[position].isSelected = false

            }else{
                holder.binding.radioCategory.setBackgroundResource(R.drawable.selector_prand)
                holder.binding.radioCategory.setTextColor(context.getColor(R.color.black))
                Log.d("Color Category Adapter0", "onBindViewHolder: Color is black" + list[position].isSelected)


            }
            holder.binding.radioCategory.setOnClickListener {
                onclickListener(list[position])

                list[position].isSelected = true

                val preferences =
                    context.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
                preferences.edit().apply {

                    putString("brand_id", list[position].id)

                }.apply()
                notifyDataSetChanged()
            }
        }


        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(val binding: ItemHomeCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}