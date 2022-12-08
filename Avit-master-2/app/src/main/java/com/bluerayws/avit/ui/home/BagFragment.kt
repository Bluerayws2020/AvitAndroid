package com.bluerayws.avit.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.BagAdapter
import com.bluerayws.avit.adapters.DeleteItemClicked
import com.bluerayws.avit.adapters.ProductAdapter
import com.bluerayws.avit.adapters.UpdateQuantity
import com.bluerayws.avit.databinding.FragmentBagBinding
import com.bluerayws.avit.dataclass.CustomerCartData
import com.bluerayws.avit.dataclass.ProductsDataMain
import com.bluerayws.avit.dataclass.TestClass
import java.util.*

class BagFragment : Fragment() {

    private var binding: FragmentBagBinding? = null

    private var adapter: BagAdapter? = null
    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var bagList: List<CustomerCartData>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        categoryVM = ViewModelProvider(this@BagFragment, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]

        binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

        val token = sharedPreferences?.getString("access_token", "")

        myBagApi()
        categoryVM.getCustomerCart(language, "Bearer $token")

        removeBagItemsApi()
        updateCartQuantity()

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    private fun myBagApi(){

        val sharedPreferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

        val token = sharedPreferences?.getString("access_token", "")

        categoryVM.getCustomerCartResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {

                    bagList = result.data.customer_cart_data
                    adapter = BagAdapter(bagList!!, requireActivity(), object : DeleteItemClicked{
                        override fun removeItem(itemId: Int) {
                            categoryVM.removeFromCart(language, bagList!![itemId].id.toString(), "Bearer $token")
                        }

                    }, object : UpdateQuantity{
                        override fun updateQuantity(position:Int, quantity: Int) {
                            categoryVM.updateQuantityCart(language, result.data.customer_cart_data[position].product_id,
                                quantity,
                                result.data.customer_cart_data[position].color_id,
                                result.data.customer_cart_data[position].size_id,
                                "Bearer $token")

                        }

                    })
                    binding?.rvBag?.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL
                        )
                    )
                    binding?.rvBag?.adapter = adapter

                    binding?.textView1645?.text  = result.data.customer_cart_header.promoCode

                    binding?.textView16?.text = result.data.customer_cart_header.subTotal

                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }


    private fun removeBagItemsApi(){

        categoryVM.deleteFromCartResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {
                    Toast.makeText(context, result.data.msg, Toast.LENGTH_SHORT).show()
                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }


    private fun updateCartQuantity(){

        categoryVM.updateQuantityCartResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {
                    Toast.makeText(context, result.data.msg, Toast.LENGTH_SHORT).show()
                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }


}