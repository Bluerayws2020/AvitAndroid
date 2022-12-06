package com.bluerayws.avit.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemCategoryBinding
import com.bluerayws.avit.dataclass.Categories


class CategoryAdapter(
    val list: List<Categories>, val context: Context, val onclickListener: (Categories) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Holder, position: Int) {



        if(HelperUtils.getLang(context) == "ar") {
            holder.binding.radioCategory.text = list[position].name_ar
        }
        else{
            holder.binding.radioCategory.text = list[position].name_en
        }


//        if (list[position].isSelected){
//            holder.binding.radioCategory.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
//            holder.binding.radioCategory.setTextColor(context.getColor(R.color.white))
//
//        }else{
//            holder.binding.radioCategory.setBackgroundResource(R.drawable.selector_prand)
//            holder.binding.radioCategory.setTextColor(context.getColor(R.color.black))

//        }
        holder.binding.radioCategory.setOnClickListener {
            onclickListener(list[position])


            val preferences =
                context.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            preferences.edit().apply {

                putString("cate_id", list[position].id)

            }.apply()
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}