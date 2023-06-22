package com.example.acronyms

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.acronyms.adapter.MeaningsAdapter
import com.example.acronyms.databinding.FragmentAcronymListBinding
import com.example.acronyms.util.ValidationUtil
import com.example.acronyms.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcronymListFragment : Fragment(), MeaningsItemClickListener,View.OnClickListener {
    private val adapter = MeaningsAdapter(this)
     private var binding: FragmentAcronymListBinding?=null
   private lateinit var  viewModel : MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentAcronymListBinding.inflate(inflater, container, false)
        viewModel =   ViewModelProvider(this)[MainViewModel::class.java]
        binding?.recyclerview?.adapter = adapter
        binding?.searchBtn?.setOnClickListener(this)


        viewModel.largeFormList.observe(viewLifecycleOwner) {
            binding?.recyclerview?.visibility=View.VISIBLE
            binding?.progressDialog?.visibility=View.GONE
            val fullFromList = mutableListOf<String>()
            for (lfItem in it[0].lfs) {
                fullFromList.add(lfItem.lf)
            }
            adapter.setAcronymList(fullFromList)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding?.progressDialog?.visibility=View.GONE
            binding?.recyclerview?.visibility=View.GONE
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        return binding?.root
    }

    override fun onClick(view: View?) {
                when (view?.id) {
            binding?.searchBtn?.id-> {
                binding?.acronymET?.hideKeyboard()
                val abbreviation = binding?.acronymET?.text.toString()
                val isValidAbbreviation = ValidationUtil.isValid(abbreviation)

                when {
                    !isValidAbbreviation.first -> {
                        Toast.makeText(requireContext(), isValidAbbreviation.second, Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> {
                        binding?.progressDialog?.visibility=View.VISIBLE
                        viewModel.getMeaningsData(abbreviation)
                        binding?.recyclerview?.smoothScrollToPosition(0)
                    }
                }
            }
        }
    }
    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onItemClick(string: String) {
       val action=
           com.example.acronyms.AcronymListFragmentDirections.actionAcronymListToDetailFragment(
               string)
        findNavController().navigate(action)
    }

}