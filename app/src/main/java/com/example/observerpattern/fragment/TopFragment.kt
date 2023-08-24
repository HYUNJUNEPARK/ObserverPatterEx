package com.example.observerpattern.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.observerpattern.R
import com.example.observerpattern.databinding.FragmentTopBinding
import com.example.observerpattern.observer.BellEventListener
import com.example.observerpattern.observer.LogoutObservable
import com.example.observerpattern.observer.PrimeCallBack

class TopFragment : Fragment() {
    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.topFragment = this@TopFragment
        return binding.root
    }

    //Sample Code1 : Java Observable Class
    fun onButtonClickedListener1() {
        LogoutObservable.notify("TopFragment Data")
    }

    //Sample Code2 : 옵저버 패턴
    //https://velog.io/@haero_kim/%EC%98%B5%EC%A0%80%EB%B2%84-%ED%8C%A8%ED%84%B4-%EA%B0%9C%EB%85%90-%EB%96%A0%EB%A8%B9%EC%97%AC%EB%93%9C%EB%A6%BD%EB%8B%88%EB%8B%A4
    fun onButtonClickedListener2() { //TopFragment 에서 이벤트 발생
        BottomFragment().catchTopFragmentEventAndRingBell(object : BellEventListener {
            override fun onRingBell(actor: String) {
                Toast.makeText(requireContext(), "벨 울린 주체자 : $actor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //Sample Code3 : 콜백(1)
    //https://velog.io/@blucky8649/%EC%BD%94%ED%8B%80%EB%A6%B0-Callback
    fun onButtonClickedListener3() {
        catchPrime(
            range = 100,
            callback = object : PrimeCallBack {
                override fun onPrimeCatched(primeNumber: Int) {
                    Log.d("testLog", "소수 : $primeNumber")
                }
            }
        )
    }

    //Sample Code3 : 콜백(2)
    fun onButtonClickedListener4() {
        primeCallback(100) { primeNumberList ->
            for (primeNumber in primeNumberList) {
                Log.d("testLog", "소수 : $primeNumber")
            }
        }
    }

    //소수로 판별이 되면 콜백함수를 호출
    private fun catchPrime(callback: PrimeCallBack, range: Int) {
        (1..range).forEach { num ->
            // 소수라고 판별되면 힘껏 외치자.
            if (isPrime(num)) callback.onPrimeCatched(num)
        }
    }

    //소수를 판별하는 함수
    private fun isPrime(num: Int): Boolean {
        for (i in 2..num / 2) {
            if (num % i == 0) return false
        }
        return true
    }

    private fun primeCallback(
        range: Int,
        callback:(ArrayList<Int>) -> Unit
    ) {
        val primeNumberList = arrayListOf<Int>()

        (1..range).forEach { num ->
            if (isPrime(num)) primeNumberList.add(num)
        }

        callback(primeNumberList)
    }
}