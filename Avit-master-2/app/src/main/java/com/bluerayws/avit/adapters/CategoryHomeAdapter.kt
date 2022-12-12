package com.bluerayws.avit.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemCategoryBinding
import com.bluerayws.avit.databinding.ItemHomeCategoryBinding
import com.bluerayws.avit.dataclass.Categories

class CategoryHomeAdapter(
    val list: List<Categories>, val context: Context, val flag: Int,
    val onclickListener: (Categories) -> Unit
    ) :
        RecyclerView.Adapter<CategoryHomeAdapter.Holder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

            val binding =
                ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)

        }


    override fun onBindViewHolder(holder: CategoryHomeAdapter.Holder, position: Int) {



        if (list[position].isSelected){
            holder.binding.radioCategory.setBackgroundColor(ContextCompat.getColor(context,
                R.color.gray1
            ))
            holder.binding.radioCategory.setTextColor(context.getColor(R.color.white))
            Log.d("Color Category Adapter", "onBindViewHolder: Color is black" + list[position].isSelected)
            list[position].isSelected = false

        }

        else{
            holder.binding.radioCategory.setBackgroundResource(R.drawable.selector_prand)
            holder.binding.radioCategory.setTextColor(context.getColor(R.color.black))
            Log.d("Color Category Adapter0", "onBindViewHolder: Color is black" + list[position].isSelected)

        }

        if(position == 0) {
            holder.binding.radioCategory.text = "الكل"
        }


         if(position > 0) {
             holder.binding.radioCategory.text = list[position].name_en
        }


        holder.binding.radioCategory.setOnClickListener {
            onclickListener(list[position])

            list[position].isSelected = true

            val preferences =
                context.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            preferences.edit().apply {

                putString("category_id", list[position].id)

            }.apply()
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemHomeCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}