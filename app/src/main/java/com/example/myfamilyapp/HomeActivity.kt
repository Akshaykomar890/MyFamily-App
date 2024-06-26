package com.example.myfamilyapp

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.myfamilyapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )

    val permissionCode = 78
    val CONTACT_PERMISSION_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(HomeFragment())
        askLocationPermission()
        askContactPermission()

        binding.navigationHome.setOnItemSelectedListener {item->
          when (item.itemId){
              R.id.firstMenue ->{
                    setFragment(HomeFragment())
                  true
              }
              R.id.secondMenue ->{
                  setFragment(MapsFragment())
                  true
              }
              R.id.thirdMenue -> {
                    setFragment(GuardFragment())
                  true
              }
              else -> true

          }
        }


    }

    private fun askContactPermission() {
        ActivityCompat.requestPermissions(this,permission,CONTACT_PERMISSION_CODE)
    }

    private fun askLocationPermission() {
        ActivityCompat.requestPermissions(this,permission,permissionCode)
    }

    fun setFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_fragment,fragment)
        transaction.commit()
    }
}