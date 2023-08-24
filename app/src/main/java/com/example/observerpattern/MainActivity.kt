package com.example.observerpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.observerpattern.databinding.ActivityMainBinding
import com.example.observerpattern.fragment.BottomFragment
import com.example.observerpattern.fragment.TopFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this@MainActivity

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView1, TopFragment())
            add(R.id.fragmentContainerView2, BottomFragment())
        }.commit()
    }
}
