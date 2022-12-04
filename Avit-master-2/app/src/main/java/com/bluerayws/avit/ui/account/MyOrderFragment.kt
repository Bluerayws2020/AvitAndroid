package com.bluerayws.avit.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluerayws.avit.adapters.MyOrderAdapter
import com.bluerayws.avit.databinding.FragmentMyOrderBinding
import com.bluerayws.avit.dataclass.TestClass

class MyOrderFragment : Fragment(), MyOrderAdapter.MyOrder {

    private var binding: FragmentMyOrderBinding? = null
    private var navController: NavController? = null
    private var adapter: MyOrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyOrderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }

        val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding?.rvMyOrder?.layoutManager = lm
        val list = ArrayList<TestClass>()
        list.add(TestClass("ee", 2))
        list.add(TestClass("ee", 2))
        list.add(TestClass("ee", 2))
        list.add(TestClass("ee", 2))
        adapter = MyOrderAdapter(list, this)
        binding?.rvMyOrder?.adapter = adapter
    }

    override fun onItemClicked() {
        val order = OrderDetailsFragment()
        order.show(childFragmentManager, "test")
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}