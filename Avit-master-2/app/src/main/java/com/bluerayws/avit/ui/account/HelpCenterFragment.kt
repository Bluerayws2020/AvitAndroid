package com.bluerayws.avit.ui.account

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.adapters.FaqsAdapter
import com.bluerayws.avit.adapters.HelpCenterAdapter
import com.bluerayws.avit.adapters.HelpCenterListener
import com.bluerayws.avit.adapters.PaymentsAdapter
import com.bluerayws.avit.databinding.FragmentHelpCenterBinding
import com.bluerayws.avit.databinding.ItemHelpCenterBinding
import com.bluerayws.avit.dataclass.Faqs
import com.bluerayws.avit.dataclass.HelpModel
import com.bluerayws.avit.dataclass.PaymentMethods
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HelpCenterFragment : Fragment() {

    private var binding: FragmentHelpCenterBinding? = null
    private var navController: NavController? = null
    private var adapter: HelpCenterAdapter? = null
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"
    private var faqsList : List<Faqs>? = null
    private var paymentList : List<PaymentMethods>? = null
    private var faqsAdapter: FaqsAdapter? = null
    private var paymentAdapter: PaymentsAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpCenterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding?.tvHelpCenterTitle?.paintFlags =
            binding?.tvHelpCenterTitle?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }

        binding?.phoneCall?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val uri = Uri.parse("tel:" + "0787663590")
            intent.data = uri
            startActivity(intent)
        }

        binding?.whatsApp?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = Uri.parse("http://api.whatsapp.com/send?phone=" + "+962788651127")
            intent.data = uri
            startActivity(intent)
        }

        binding?.email?.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            val uri = Uri.parse("mailto:" + "test@gmail.com")
            intent.data = uri
            startActivity(intent)
        }


        faqsApi()
        deliveryApi()
        paymentMethodsApi()

        let {//help center adapter
            val lm = GridLayoutManager(requireActivity(), 2)
            binding?.rvHelpCenter?.layoutManager = lm
            val list: ArrayList<HelpModel> = ArrayList()
            list.add(HelpModel(getString(R.string.popular_question), R.drawable.ic_help_chat_question))
            list.add(HelpModel(getString(R.string.order), R.drawable.ic_help_order_box))
            list.add(HelpModel(getString(R.string.influencer), R.drawable.ic_help_heart_hand))
            list.add(HelpModel(getString(R.string.shipping_delivery), R.drawable.ic_help_truck))
            list.add(HelpModel(getString(R.string.membership_registration), R.drawable.ic_help_id_card_identity))
            list.add(HelpModel(getString(R.string.payment_method), R.drawable.ic_help_payment_method))
            adapter = HelpCenterAdapter(list, object : HelpCenterListener{
                override fun onClick(position: Int) {
                    when(position){

                        0 -> {
                            userVM.getPopularQuestions(language)
                        }
                        1 -> {
                            // get orders
                        }
                        2 -> {
                            // influencers
                        }
                        3 -> {
                            userVM.getDelivery(language)
                        }
                        4 -> {
                            // Membership Registration
                        }
                        5 -> {
                            userVM.getPaymentMethods(language)
                        }
                    }
                }

            })
            binding?.rvHelpCenter?.adapter = adapter
        }


    }



    private fun faqsApi(){


        userVM.getPopularQuestionsResponse().observe(viewLifecycleOwner){result ->
            val builder = AlertDialog.Builder(this.context)
            val inflater = layoutInflater

            builder.setTitle("FaQs")

            val dialogLayout = inflater.inflate(R.layout.faqs_popup_window, null)
            val recView = dialogLayout.findViewById<RecyclerView>(R.id.rvFaqs)

            builder.setView(dialogLayout)

            val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recView?.layoutManager = lm
            when (result) {
                is NetworkResults.Success -> {
                    faqsList = result.data.faqs

                    faqsAdapter = FaqsAdapter(faqsList!!, requireContext()) //response?.body()?.termsAndConditions
                    recView?.adapter = faqsAdapter

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }


            builder.show()
            builder.create()

        }

    }

//    getDeliveryResponse


    private fun deliveryApi(){


        userVM.getDeliveryResponse().observe(viewLifecycleOwner){result ->
            val builder = AlertDialog.Builder(this.context)
            val inflater = layoutInflater

            builder.setTitle("Shipping Delivery")

            val dialogLayout = inflater.inflate(R.layout.delivery_popup_window, null)
            val detailTv = dialogLayout.findViewById<TextView>(R.id.tvDetails)
            val detailImg = dialogLayout.findViewById<ImageView>(R.id.deliveryImg)

            builder.setView(dialogLayout)

            when (result) {
                is NetworkResults.Success -> {

                    Glide.with(requireContext())
                        .load(result.data.delivery.image)
                        .placeholder(R.drawable.ic_check)
                        .error(R.drawable.ic_check)
                        .into(detailImg!!)

                    if(HelperUtils.getLang(context) == "ar")
                    {
                        detailTv.text = result.data.delivery.details_ar.toString()
                    }
                    else{
                        detailTv.text = result.data.delivery.details_en.toString()

                    }




                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }


            builder.show()
            builder.create()

        }

    }




    private fun paymentMethodsApi(){


        userVM.getPaymentMethodsResponse().observe(viewLifecycleOwner){result ->

            val builder = AlertDialog.Builder(this.context)
            val inflater = layoutInflater

            builder.setTitle("Payment Methods")

            val dialogLayout = inflater.inflate(R.layout.faqs_popup_window, null)
            val logo = dialogLayout.findViewById<ImageView>(R.id.checkbox)
            val recView = dialogLayout.findViewById<RecyclerView>(R.id.rvFaqs)

            logo.setImageResource(R.drawable.ic_help_payment_method)

            builder.setView(dialogLayout)

            val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recView?.layoutManager = lm
            when (result) {
                is NetworkResults.Success -> {
                    paymentList = result.data.payment_methods

                    paymentAdapter = PaymentsAdapter(paymentList!!, requireContext()) //response?.body()?.termsAndConditions
                    recView?.adapter = paymentAdapter

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }


            builder.show()
            builder.create()

        }

    }

}