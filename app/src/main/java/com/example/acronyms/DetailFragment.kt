package com.example.acronyms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.acronyms.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private val args : DetailFragmentArgs by navArgs()
    private var binding: FragmentDetailBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentDetailBinding.inflate(inflater, container, false)
        binding?.fullFromTV?.text=args.fullFrom
        return binding?.root
    }

}