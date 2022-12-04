package com.bluerayws.avit.ui.account

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.FragmentOrderDetailsBinding
import com.bluerayws.avit.databinding.ItemDetailsLineBinding
import com.bluerayws.avit.databinding.ItemMyProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderDetailsFragment : BottomSheetDialogFragment() {

    private var binding: FragmentOrderDetailsBinding? = null
    private lateinit var lineAdapter: LineAdapter
    private lateinit var myProductAdapter: MyProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.tvOrderDetails?.paintFlags =
            binding?.tvOrderDetails?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
        binding?.ivClose?.setOnClickListener {
            dismiss()
        }

        let {
            val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val lm2 = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            binding?.rvLine?.layoutManager = lm
            val list: ArrayList<LineOrder> = ArrayList()
            list.add(LineOrder(0, getString(R.string.your_request_received)))
            list.add(LineOrder(0, getString(R.string.waiting_choose_payment)))
            list.add(LineOrder(0, getString(R.string.work_is_done_upon)))
            list.add(LineOrder(0, getString(R.string.your_order_processed)))
            list.add(LineOrder(1, getString(R.string.your_order_shipping)))
            list.add(LineOrder(0, getString(R.string.order_on_way)))
            list.add(LineOrder(0, getString(R.string.arrived_your_country)))
            list.add(LineOrder(0, getString(R.string.order_on_way)))
            list.add(LineOrder(0, getString(R.string.order_delivered)))

            lineAdapter = LineAdapter(list, requireActivity())
            binding?.rvLine?.adapter = lineAdapter

            myProductAdapter = MyProductAdapter(list)
            binding?.rvMyProduct?.layoutManager = lm2
            binding?.rvMyProduct?.adapter = myProductAdapter
        }
    }

    class LineAdapter(val list: ArrayList<LineOrder>, val context: Context) :
        RecyclerView.Adapter<LineAdapter.Holder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding =
                ItemDetailsLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = list[position]
            holder.binding.tvTitle.text = data.title

            when {
                position == 0 ->
                    holder.binding.line.visibility = View.GONE
                data.status == 1 -> {
                    holder.binding.circle.setBackgroundResource(R.drawable.ic_circle_line_2)
                    holder.binding.tvTitle.setTextColor(Color.BLACK)
                }

            }


        }

        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(val binding: ItemDetailsLineBinding) : RecyclerView.ViewHolder(binding.root)
    }

    class MyProductAdapter(val list: ArrayList<LineOrder>) :
        RecyclerView.Adapter<MyProductAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding =
                ItemMyProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {

        }

        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(val binding: ItemMyProductBinding) : RecyclerView.ViewHolder(binding.root)
    }

    data class LineOrder(val status: Int, val title: String)
}