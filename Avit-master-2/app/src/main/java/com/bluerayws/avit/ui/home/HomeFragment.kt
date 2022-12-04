package com.bluerayws.avit.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.CategoryAdapter
import com.bluerayws.avit.adapters.ProductAdapter
import com.bluerayws.avit.adapters.ItemClicked
import com.bluerayws.avit.adapters.ProductHomeAdapter
import com.bluerayws.avit.databinding.FragmentHomeBinding
import com.bluerayws.avit.dataclass.ProductsDataMain
import com.bluerayws.avit.dataclass.ProductsHomeMain
import com.bluerayws.avit.dataclass.TestClass
import com.bluerayws.avit.ui.activities.HomeActivity
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bluerayws.avit.ui.activities.SearchActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class HomeFragment : Fragment(), ItemClicked {

    private var binding: FragmentHomeBinding? = null
    private lateinit var imageList: ArrayList<SlideModel>
    private var adapter: ProductAdapter? = null
    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var homeList: List<ProductsDataMain>? = null
//    private var productHomeAdapter: ProductHomeAdapter? = null


    companion object {
        const val CATEGORY_ID = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (HomeActivity.ComeFromLogin) {
            HomeActivity.ComeFromLogin = false
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment2)

        }

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryVM = ViewModelProvider(this, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]


        val sharedPreferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("access_token", "defaultname")
        Log.d("token home: ", "onViewCreated: $token")


        val preferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
        val catId = preferences?.getString("cate_id", "cate_id")
        val categoryId = preferences?.getString("category_id", "category_id")

        sliderImage()
        radioButton()
        binding?.search?.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }

        getProductsByCateIdApi()

        Log.d("catId1", "onViewCreated: $categoryId")
        Log.d("catId2", "onViewCreated: $catId")

        categoryVM.getProductsByCateId(language, catId.toString())

//        val lm = GridLayoutManager(activity, 2)
//        binding?.rvHome?.layoutManager = lm
//        val list = java.util.ArrayList<TestClass>()
//        list.add(TestClass("Name", R.drawable.ic_facebook_seq))
//        list.add(TestClass("Name", R.drawable.ic_snapchat))
//        list.add(TestClass("Name", R.drawable.ic_tiktok))
//        list.add(TestClass("Name", R.drawable.ic_whatsapp))
//        list.add(TestClass("Name", R.drawable.ic_instagram))
//        list.add(TestClass("Name", R.drawable.ic_youtube))
//        list.add(TestClass("Name", R.drawable.ic_linkedin))
//        list.add(TestClass("Name", R.drawable.ic_twitter_circle))
//        adapter = ProductAdapter(list, this)
//
//        binding?.rvHome?.adapter = adapter
    }





    private fun sliderImage() {
        imageList = ArrayList() // Create image list
        imageList.add(SlideModel(R.drawable.img_artboard))
        imageList.add(SlideModel(R.drawable.img_artboard))
        imageList.add(SlideModel(R.drawable.img_artboard))
        binding?.imageSlider?.setImageList(imageList, ScaleTypes.FIT)
    }

    private fun radioButton() {
        binding?.radioAvit?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding?.radioAvit?.setTextColor(Color.parseColor("#FFFFFFFF"))
            } else {
                binding?.radioAvit?.setTextColor(Color.BLACK)
            }
        }
        binding?.radioEvika?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding?.radioEvika?.setTextColor(Color.parseColor("#FFFFFFFF"))
            } else {
                binding?.radioEvika?.setTextColor(Color.BLACK)
            }
        }

    }

    override fun onItemClicked() {
        startActivity(Intent(requireActivity(), ProductActivity::class.java))
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun getProductsByCateIdApi(){

        val lm = GridLayoutManager(context, 2)

        categoryVM.getProductsByCateIdResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {



                    homeList = result.data.products_data
                    binding?.rvHome?.layoutManager = lm
                    val productHomeAdapter = ProductHomeAdapter(homeList!!, requireContext())
                    binding?.rvHome?.adapter = productHomeAdapter

                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }




}
/*Social Media

    private lateinit var rvSocial: RecyclerView

        rvSocial = binding.rvSocial

 val adapter2=SocialMediaAdapter(list,requireActivity())
        val lm2=GridLayoutManager(activity,4)
        rvSocial.layoutManager=lm2
        rvSocial.adapter=adapter2
*
* */