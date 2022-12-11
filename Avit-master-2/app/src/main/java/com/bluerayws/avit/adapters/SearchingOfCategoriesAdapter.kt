package com.bluerayws.avit.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemCategoryBinding
import com.bluerayws.avit.databinding.ItemFavoriteBinding
import com.bluerayws.avit.dataclass.Categories
import com.bluerayws.avit.dataclass.CategoriesList
import com.bluerayws.avit.dataclass.ProductsOfSearching
import com.bluerayws.avit.dataclass.SearchOfCategories
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bumptech.glide.Glide

class SearchingOfCategoriesAdapter (private val list: List<CategoriesList>,
                                    private val context: Context,
                                    val onclickListener: (CategoriesList) -> Unit) :

    RecyclerView.Adapter<SearchingOfCategoriesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]



        holder.binding.radioCategory.text = data.name_en


        holder.binding.radioCategory.setOnClickListener {
            onclickListener(data)


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

    class Holder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}