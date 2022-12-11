package com.bluerayws.avit.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.CategoryAdapter
import com.bluerayws.avit.databinding.FragmentCategoryBinding
import com.bluerayws.avit.dataclass.Categories
import com.bluerayws.avit.ui.activities.SearchActivity

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null
//    private val categoryVM by viewModels<CategroyViewModel>()
    private val language = "ar"
    private var categoryAdapter: CategoryAdapter? = null

//   1- Mvvm
    private lateinit var categoryVM :CategoryViewModel
    private var cateList : List<Categories>? =null
    private val categoryRepo = CMainRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryVM = ViewModelProvider(this, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]

        binding?.search?.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
            SearchActivity.ComeFromCategory = true
        }

        categoryApi()

        val preferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

        binding?.tvAll?.text  = "الكل"
        binding?.tvAll?.setOnClickListener {

            preferences?.edit()?.apply {
                putString("category_id", "0")
            }?.apply()

            findNavController().navigate(
                R.id.action_categoryFragment_to_homeFragment)

            Log.d("Category = ", "onViewCreated: " + HomeFragment.CATEGORY_ID)
        }


        categoryVM.getCategories(language)
        

    }

    private fun categoryApi(){
//        val apiInstanse = ApiInstanse
        val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        categoryVM.getCategoriesResponse().observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResults.Success -> {


                    cateList = result.data.categories
                    binding?.rvCategory?.layoutManager = lm


                    val categoryAdapter = CategoryAdapter(cateList!!, requireActivity()) { it ->
                        findNavController().navigate(
                            R.id.action_categoryFragment_to_homeFragment,
                            bundleOf(Pair(HomeFragment.CATEGORY_ID, it.id))
                        )
                    }

                    binding?.rvCategory?.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL,
                        )
                    )

                    binding?.rvCategory?.adapter = categoryAdapter

                }
                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }
}











//        val list = ArrayList<Category_Array>()
////        list.add(TestClass("name", 787663762))
////        list.add(TestClass("name", 787663762))
////        list.add(TestClass("name", 787663762))
////        list.add(TestClass("name", 787663762))
////        list.add(TestClass("name", 787663762))
//
////        retrofit call
//
//
////        val apiInstanse = ApiClient.getInstance()
//
//        val categroyArrat = CMainRepo
//
////        connect  View Model with ViewModelProvider
//
//        categoryVM = ViewModelProvider(this,CategroyViewModelFactory(categroyArrat)).get(CategroyViewModel::class.java)
//
//        categoryVM.categoryArray.observe(viewLifecycleOwner) { it ->
////            if (it.status == "false") {
////
////                Toast.makeText(context, it.msg.toString(), Toast.LENGTH_LONG).show()
////            } else {
//                it.categories.iterator().forEach {
////                    list.add(Category_Array(it.cid, it.name_ar))
//                    list.add(Category_Array(it.cid,it.name_ar))
//                }
//                Toast.makeText(context, "Data", Toast.LENGTH_LONG).show()
//
//            val adapter =  CategoryAdapter(list, requireActivity())
//            binding?.rvCategory?.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
////            notifyDataSetChanged()
//            binding?.rvCategory?.adapter = adapter
////            }
//        }
