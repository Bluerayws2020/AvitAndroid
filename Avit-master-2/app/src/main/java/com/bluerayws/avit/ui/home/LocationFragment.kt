package com.bluerayws.avit.ui.home

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.LocationAdapter
import com.bluerayws.avit.databinding.FragmentLocationBinding
import com.bluerayws.avit.dataclass.StoreDetails
import com.bluerayws.avit.dataclass.TestClass2
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import java.util.*

private const val LOCATION_OPEN = 1

//TODO replace map with mapView because the screen is fragment
class LocationFragment : Fragment(), OnMapReadyCallback, LocationAdapter.LocationClicked {

    private var binding: FragmentLocationBinding? = null
    private var navController: NavController? = null
    private lateinit var googleMap: GoogleMap
    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var adpter: LocationAdapter

    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var list: List<StoreDetails> ? = null

    private val requestPermissionLocation =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (!it)
                navController?.popBackStack()
        }
    private val requestPermissionCall =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkGPS()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryVM = ViewModelProvider(this@LocationFragment, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]


        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

//        val lm = GridLayoutManager(requireActivity(), 1)
//        binding?.rvLocation?.layoutManager = lm
//        val list = ArrayList<TestClass2>()
//        list.add(TestClass2("AVIT Fashoin Store - Marka", "0787663762"))
//        list.add(TestClass2("AVIT Fashoin Store - Marka", "0787663762"))
//        list.add(TestClass2("AVIT Fashoin Store - Marka", "0787663762"))
//        list.add(TestClass2("AVIT Fashoin Store - Marka", "ssss"))
//
//        adpter = LocationAdapter(list, requireActivity(), this)
//
//        binding?.rvLocation?.addItemDecoration(
//            DividerItemDecoration(
//                requireContext(),
//                RecyclerView.VERTICAL
//            )
//        )
//        binding?.rvLocation?.adapter = adpter



    // get Stores

        getStoresLocation()
        categoryVM.getStores(language)


    }


    override fun onMapReady(p0: GoogleMap) {
        p0.let {
            googleMap = it

            let {
                if (ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    return
                }
            }//getPermission
            googleMap.isMyLocationEnabled = true
            //googleMap.uiSettings.isZoomControlsEnabled = true

            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationProviderClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
                val current = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 10f))
                //start map in my location
            }

            //TODO(add my marks from database)
            val latLong = LatLng(31.94466, 35.853656)
            googleMap.addMarker(MarkerOptions().position(latLong).title("test mark"))
        }
    }

    private fun checkGPS() {
        locationRequest = LocationRequest.create()
        locationRequest.apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 2 * 1000
            fastestInterval = 1 * 1000
        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {
        }
        task.addOnFailureListener {
            if (it is ResolvableApiException) {
                try {
                    it.startResolutionForResult(requireActivity(), LOCATION_OPEN)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Toast.makeText(requireActivity(), getString(R.string.error), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onCallClicked(data: String) {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionCall.launch(Manifest.permission.CALL_PHONE)
        } else {
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            val uri = Uri.parse("tel:" + data.toString())
            intent.data = uri
            startActivity(intent)
        }

    }

    override fun onDirectionClicked(data: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        val uri = Uri.parse("google.navigation:q=31.94466,35.853656&mode=d")
        intent.data = uri
        intent.`package` = "com.google.android.apps.maps"
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    private fun getStoresLocation(){

        val lm = GridLayoutManager(requireActivity(), 1)

        categoryVM.getStoresResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {

                    binding?.rvLocation?.layoutManager = lm

                    list = result.data.stores

                    adpter = LocationAdapter(list!!, requireActivity(), this)

                    binding?.rvLocation?.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL
                        )
                    )
                    binding?.rvLocation?.adapter = adpter

                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }

    }
}