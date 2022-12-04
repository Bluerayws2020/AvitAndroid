package com.bluerayws.avit.ui.account

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluerayws.avit.adapters.ExpandAdapter
import com.bluerayws.avit.databinding.FragmentMyPointBinding

class MyPointFragment : Fragment() {

    private var binding: FragmentMyPointBinding? = null
    private var navController: NavController? = null
    private var adapter: ExpandAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPointBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding?.tvMyPointTitle?.paintFlags =
            binding?.tvMyPointTitle?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }

        val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding?.rvMyPoint?.layoutManager = lm
//        adapter = ExpandAdapter()
        binding?.rvMyPoint?.adapter = adapter
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}