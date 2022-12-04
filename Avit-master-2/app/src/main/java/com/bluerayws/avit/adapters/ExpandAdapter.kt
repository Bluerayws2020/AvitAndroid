package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.databinding.ItemExpandBinding
import com.bluerayws.avit.dataclass.TermsConditions

class ExpandAdapter(private val list: List<TermsConditions>, val context: Context) : RecyclerView.Adapter<ExpandAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemExpandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]

        holder.binding.ivShow.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                holder.binding.tvDescription.visibility = View.VISIBLE
            else
                holder.binding.tvDescription.visibility = View.GONE
        }


        if(HelperUtils.getLang(context) == "ar") {
            holder.binding.tvTitle.text = data.term_and_condition_title_ar.toString()
            holder.binding.tvDescription.text = data.term_and_condition_des_ar.toString()
        }
        else{
            holder.binding.tvTitle.text = data.term_and_condition_title_en.toString()
            holder.binding.tvDescription.text = data.term_and_condition_des_en.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val binding: ItemExpandBinding) : RecyclerView.ViewHolder(binding.root)
}
