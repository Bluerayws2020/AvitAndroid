package com.bluerayws.avit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.adapters.BagAdapter
import com.bluerayws.avit.databinding.FragmentBagBinding
import com.bluerayws.avit.dataclass.TestClass
import java.util.*

class BagFragment : Fragment() {

    private var binding: FragmentBagBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = TestClass("name", 787663762)
        val list = ArrayList<TestClass>()
        list.add(TestClass("name", 787663762))
        list.add(TestClass("name", 787663762))
        list.add(TestClass("name", 787663762))
        list.add(TestClass("name", 787663762))
        list.add(TestClass("name", 787663762))

        val adapter = BagAdapter(list, requireActivity())
        binding?.rvBag?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        binding?.rvBag?.adapter = adapter
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}