package com.example.observerpattern.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.observerpattern.R
import com.example.observerpattern.databinding.FragmentTopBinding
import com.example.observerpattern.observer.EventListener
import com.example.observerpattern.observer.LogoutObservable

class TopFragment : Fragment() {
    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.topFragment = this@TopFragment
        return binding.root
    }

    //Sample Code1
    fun onButtonClickedListener() {
        LogoutObservable.notify("TopFragment Data")
    }

    //Sample Code2 : https://velog.io/@haero_kim/%EC%98%B5%EC%A0%80%EB%B2%84-%ED%8C%A8%ED%84%B4-%EA%B0%9C%EB%85%90-%EB%96%A0%EB%A8%B9%EC%97%AC%EB%93%9C%EB%A6%BD%EB%8B%88%EB%8B%A4
    fun startEvent() { //TopFragment 에서 이벤트 발생
        BottomFragment().catchTopFragmentEventAndRingBell(object : EventListener {
            override fun onRingBell(actor: String) {
                Toast.makeText(requireContext(), "벨 울린 주체자 : $actor", Toast.LENGTH_SHORT).show()
            }
        })
    }
}