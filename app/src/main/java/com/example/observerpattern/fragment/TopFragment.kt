package com.example.observerpattern.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.observerpattern.R
import com.example.observerpattern.databinding.FragmentTopBinding
import com.example.observerpattern.observer.LogoutObservable

//이벤트 발생 주체
class TopFragment : Fragment() {
    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.topFragment = this@TopFragment
        return binding.root
    }

    fun onButtonClickedListener() {
        LogoutObservable.notify("TopFragment Data")
    }
}