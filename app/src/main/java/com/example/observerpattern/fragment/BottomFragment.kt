package com.example.observerpattern.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.observerpattern.R
import com.example.observerpattern.databinding.FragmentBottomBinding
import com.example.observerpattern.observer.BellEventListener
import com.example.observerpattern.observer.LogoutObservable

class BottomFragment : Fragment() {
    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom, container, false)
        binding.bottomFragment = this@BottomFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Sample Code1
        LogoutObservable.addObserver { _, obj ->
            val dataFromTopFragment = obj as String
            Toast.makeText(requireContext(), "$dataFromTopFragment", Toast.LENGTH_SHORT).show()
        }
    }

    //Sample Code2
    fun catchTopFragmentEventAndRingBell(bellEventListener: BellEventListener) {
        bellEventListener.onRingBell("BottomFragment")
    }
}
