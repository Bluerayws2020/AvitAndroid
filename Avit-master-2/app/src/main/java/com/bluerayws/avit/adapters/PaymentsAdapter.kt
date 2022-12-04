package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.databinding.ItemsFaqsBinding
import com.bluerayws.avit.dataclass.Faqs
import com.bluerayws.avit.dataclass.PaymentMethods

class PaymentsAdapter(private val paymentList: List<PaymentMethods>, private val context: Context) : RecyclerView.Adapter<PaymentsAdapter.Holder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ItemsFaqsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }


        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = paymentList[position]

            if(HelperUtils.getLang(context) == "ar"){
                holder.binding.tvTitle.text = data.title_ar
                holder.binding.tvDescription.text = data.answer_ar
            }
            else{
                holder.binding.tvTitle.text = data.title_en
                holder.binding.tvDescription.text = data.answer_en
            }

        }


        override fun getItemCount(): Int {
            return paymentList.size
        }

        class Holder(val binding: ItemsFaqsBinding) : RecyclerView.ViewHolder(binding.root)
    }
