package com.bluerayws.avit.ui.account

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.adapters.AddressAdapter
import com.bluerayws.avit.adapters.AddressClicked
import com.bluerayws.avit.databinding.FragmentPersonalInfoBinding
import com.bluerayws.avit.dataclass.CustomerData
import com.bluerayws.avit.dataclass.LocationsCustomers
import com.bluerayws.avit.dataclass.LocationsData
import com.bumptech.glide.Glide
import id.zelory.compressor.Compressor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException

class PersonalInfoFragment : Fragment() {

    private var binding: FragmentPersonalInfoBinding? = null
    private var navController: NavController? = null
    private var adapter: AddressAdapter? = null
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"
    private var addressList: List<LocationsData>? = null


    companion object{
        const val IMAGE_REQUEST_CODE = 100
    }

    private var mediaPath: String? = null
    private var postPath: String? = null


//    private var cameraImg =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == RESULT_OK && it.data != null) {
//                val bitmap = it.data!!.extras!!.get("data") as Bitmap
//                binding?.circleImageView2?.setImageBitmap(bitmap)
//            }
//        }
//    private val galleryImg =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == RESULT_OK && it.data != null) {
//                val bitmap: Bitmap
//                try {
//                    val inputStream =
//                        requireActivity().contentResolver.openInputStream(it.data!!.data!!)
//                    bitmap = BitmapFactory.decodeStream(inputStream)
//                    binding?.circleImageView2?.setImageBitmap(bitmap)
//                } catch (e: FileNotFoundException) {
//                    e.printStackTrace()
//                }
//            }
//        }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        binding?.tvPersonalTitle?.paintFlags =
            binding?.tvPersonalTitle?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }


        val sharedPreferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("access_token", "defaultname")
        Log.d("token home: 3", "onViewCreated: " + token.toString())



        personalApi()
        userVM.userInfo(language, "Bearer $token")



        updateUserInfoApi()
        updateUserPasswordApi()
        getLocationsCustomers()


        binding?.ivPersonalEditImage?.setOnClickListener {
            Toast.makeText(requireContext(), "edit", Toast.LENGTH_SHORT).show()
//            userVM.updateUserData(
//                language,
//                binding?.tvPersonalName?.text.toString(),
//                binding?.tvPersonalName?.text.toString(),
//                binding?.tvPersonalName?.text.toString(),
//                binding?.tvPersonalEmail?.text.toString(),
//                binding?.tvPersonalPhone?.text.toString(),
//                "4",
//                "4",
//                address = "",
//                "Bearer $token"
//            )
            pickImageGallery()
        }

        binding?.ivPersonalEditName?.setOnClickListener {
            withEditTextName()
        }

        binding?.ivPersonalEditPhone?.setOnClickListener {
            withEditTextPhone()
        }

        binding?.ivPersonalEditEmail?.setOnClickListener {
            withEditTextEmail()
        }

        binding!!.ivPersonalEditPass.setOnClickListener {
            withEditTextPassword()
        }

        //address Adapter
        let {
            val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            binding?.rvAddress?.layoutManager = lm

            userVM.getLocationsCustomers(language, "Bearer $token")

            if (addressList != null) {
                adapter = AddressAdapter(addressList!!)
                binding?.rvAddress?.adapter = adapter
            }
            else{
                Toast.makeText(requireContext(), "The Address List is Null", Toast.LENGTH_SHORT).show()
            }
        }
    }





    private fun personalApi(){
        userVM.getUserInfoResponse().observe(viewLifecycleOwner){result ->
            when (result) {
                is NetworkResults.Success -> {
                    binding?.tvPersonalName?.text  = result.data.data.name_ar
                    binding?.tvPersonalPhone?.text = result.data.data.phone
                    binding?.tvPersonalEmail?.text = result.data.data.email


                    Glide.with(requireContext())
                        .load(result.data.data.profile_photo)
                        .placeholder(R.drawable.ic_profile_user_account)
                        .error(R.drawable.ic_profile_user_account)
                        .into(binding?.circleImageView2!!)
//                    addressValues = result.data.data
//                    val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//                    binding?.rvAddress?.layoutManager = lm
//
//                    adapter = AddressAdapter(addressValues!!,
//                        object : AddressClicked{
//                            override fun onClick(position: Int) {
//                                when (position) {
//                                    position -> {
//                                        withEditTextName()
//                                    }
//
//                                }
//
//                            }
//                        })
//
//                    binding?.rvAddress?.adapter = adapter


                    Log.d("password", "personalApi: " + binding?.tvPersonalPass?.text)
//                    binding?.circleImageView2. // Glide
                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }


            }

        }
    }


//    private fun takeImage() {
//        val dialog = AlertDialog.Builder(requireActivity()).setTitle(getString(R.string.choose))
//            .setPositiveButton(
//                getString(R.string.camera)
//            ) { dialog, which ->
//                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                cameraImg.launch(intent)
//            }.setNegativeButton(
//                getString(R.string.gallery)
//            ) { dialog, which ->
//                val intent =
//                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                galleryImg.launch(intent)
//
//            }.create()
//        dialog!!.window!!.setBackgroundDrawable(
//            AppCompatResources.getDrawable(
//                requireContext(),
//                R.drawable.shape_dialog_raduis
//            )
//        )
//        dialog.show()
//    }



    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }




    private fun withEditTextName() {
        val builder = AlertDialog.Builder(this.context)
        val inflater = layoutInflater
        builder.setTitle("Edit Name")
        val dialogLayout = inflater.inflate(R.layout.popup_window, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        editText?.setText(binding?.tvPersonalName?.text.toString())
        builder.setView(dialogLayout)
        builder.setPositiveButton("Apply") { dialogInterface, i ->
            Toast.makeText(context, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT)
                .show()
            val newName = editText.text.toString()
            binding?.tvPersonalName?.text = newName

            val sharedPreferences =
                context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("access_token", "defaultname")



            userVM.updateUserData(language,newName, newName,
                username = "$newName+ $newName",
                email = binding?.tvPersonalEmail?.text.toString(),
                binding?.tvPersonalPhone?.text.toString(),
                "HH541", "H569", "IRBID", "Bearer $token")


        }
        builder.show()
        builder.create()
    }

    private fun withEditTextPhone(){

        val builder = AlertDialog.Builder(this.context)
        val inflater = layoutInflater
        builder.setTitle("Edit Name")
        val dialogLayout = inflater.inflate(R.layout.popup_window, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        editText?.setText(binding?.tvPersonalPhone?.text.toString())
        builder.setView(dialogLayout)
        builder.setPositiveButton("Apply") { dialogInterface, i ->
            Toast.makeText(context, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT)
                .show()
            val newPhone = editText.text.toString()
            binding?.tvPersonalPhone?.text = newPhone

            val sharedPreferences =
                context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("access_token", "defaultname")



            userVM.updateUserData(language,binding?.tvPersonalName?.text.toString(), binding?.tvPersonalName?.text.toString(),
                username = binding?.tvPersonalName?.text.toString(),
                email = binding?.tvPersonalEmail?.text.toString(),
                newPhone,
                "HH541", "H569", "IRBID", "Bearer $token")


        }
        builder.show()
        builder.create()

    }

    private fun withEditTextEmail() {
        val builder = AlertDialog.Builder(this.context)
        val inflater = layoutInflater
        builder.setTitle("Edit Name")
        val dialogLayout = inflater.inflate(R.layout.popup_window, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        editText?.setText(binding?.tvPersonalEmail?.text.toString())
        builder.setView(dialogLayout)
        builder.setPositiveButton("Apply") { dialogInterface, i ->
            Toast.makeText(context, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT)
                .show()
            val newEmail = editText.text.toString()
            binding?.tvPersonalEmail?.text = newEmail

            val sharedPreferences =
                context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("access_token", "defaultname")



            userVM.updateUserData(language,binding?.tvPersonalName?.text.toString(), binding?.tvPersonalName?.text.toString(),
                binding?.tvPersonalName?.text.toString(),
                newEmail,
                binding?.tvPersonalPhone?.text.toString(),
                "HH541", "H569", "IRBID", "Bearer $token")



        }
        builder.show()
        builder.create()
    }



    private fun withEditTextPassword() {

        val builder = AlertDialog.Builder(this.context)
        val inflater = layoutInflater

        builder.setTitle("Change Password")

        val dialogLayout = inflater.inflate(R.layout.popup_window_password, null)
        val editText1  = dialogLayout.findViewById<EditText>(R.id.editText1)
        editText1.hint = "Current Password"

        val editText2  = dialogLayout.findViewById<EditText>(R.id.editText2)
        editText2.hint = "New Password"

        val editText3  = dialogLayout.findViewById<EditText>(R.id.editText3)
        editText3.hint = "Confirm New Password"


        builder.setView(dialogLayout)


        builder.setPositiveButton("Apply") { dialogInterface, i ->
            Toast.makeText(context, "EditText is " + editText1.text.toString(), Toast.LENGTH_SHORT)
                .show()

            val preferences = context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
            val token =  preferences?.getString("access_token", "access_token")


            val current_pass = editText1.text.toString()
            val new_pass = editText2.text.toString()
            val confirm_pass = editText3.text.toString()


            userVM.resetUserPassword(language, current_pass, new_pass, confirm_pass,"Bearer $token")

        }
        builder.show()
        builder.create()
    }


    private fun updateUserInfoApi(){
        userVM.getUpdateUserInfoResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
//                    Toast.makeText(requireContext(), result.data.customer_profile_data.username.toString(), Toast.LENGTH_SHORT).show()

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }



    private fun updateUserPasswordApi(){
        userVM.getResetPasswordResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(requireContext(), result.data.msg.toString(), Toast.LENGTH_SHORT).show()

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }



    private fun getLocationsCustomers(){
        userVM.getLocationsCustomersResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(requireContext(), result.data.success.toString(), Toast.LENGTH_SHORT).show()
                    addressList = result.data.data

                    Log.d("Address List Contains: ", "getLocationsCustomers: $addressList")
                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }




    @SuppressLint("IntentReset")
    private fun pickImageGallery() {

        activity?.let {
            if (ContextCompat.checkSelfPermission(it.applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
            else {
                val pickImageIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                pickImageIntent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
                pickImageIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                startActivityForResult(pickImageIntent, IMAGE_REQUEST_CODE)

            }
        }
    }

    private val PERMISSIONS_LENGTH = 2
    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<out String>, grantResults:IntArray) {
        // Check whether requestCode is set to the value of CAMERA_REQ_CODE during permission application, and then check whether the permission is enabled.

        if(requestCode == 1 )
        {
            if(grantResults.isNotEmpty() && grantResults.size
                == PERMISSIONS_LENGTH && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] != PackageManager.PERMISSION_GRANTED)
            {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
                Log.i("REQUEST2CODE", "onActivityResult: $requestCode")
            }
        }else {
            Toast.makeText(context,"Permission Denied",Toast.LENGTH_LONG).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("Recycle")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        Log.i("REQUEST CODE", "onActivityResult: $requestCode")
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            if (data != null) {
                // Get the Image from data
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)


                val cursor = context?.contentResolver?.query(
                    selectedImage!!,
                    filePathColumn,
                    null,
                    null,
                    null
                )
                assert(cursor != null)
                cursor!!.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath = cursor.getString(columnIndex)


                binding?.circleImageView2?.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
                postPath = mediaPath

                val imageFile = File(mediaPath.toString())

                Log.i("postPath", "onActivityResult:  $imageFile")


                lifecycleScope.launch {

                    val compressedImageFile =
                        Compressor.compress(requireContext(), imageFile, Dispatchers.IO)

                    val preferences = context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
                    val token =  preferences?.getString("access_token", "access_token")

                    userVM.updateUserData(
                        language,
                        binding?.tvPersonalName?.text.toString(),
                        binding?.tvPersonalName?.text.toString(),
                        binding?.tvPersonalName?.text.toString(),
                        binding?.tvPersonalEmail?.text.toString(),
                        binding?.tvPersonalPhone?.text.toString(),
                        "1-1---1-1-1---1--1~1",
                        "1-1---1-1-1---1--1~1",
                        "Irbid",
                        "Bearer $token",
                        compressedImageFile
                    )

                }

            } else if (resultCode != Activity.RESULT_CANCELED) {
                Toast.makeText(
                    requireContext(),
                    "Sorry, there was an error!",
                    Toast.LENGTH_LONG
                ).show()
            }

        } }

}