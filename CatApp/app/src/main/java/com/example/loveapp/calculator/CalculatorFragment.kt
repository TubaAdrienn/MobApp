package com.example.loveapp.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.loveapp.R
import com.example.loveapp.dao.LoveDatabase
import com.example.loveapp.databinding.CalculatorBinding


class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CalculatorBinding = DataBindingUtil.inflate(
            inflater, R.layout.calculator, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = LoveDatabase.getInstance(application).loveDao

        val viewModelFactory = CalculatorViewModelFactory(dataSource)

        val calcViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CalculatorViewModel::class.java)

        binding.calculatorViewModel = calcViewModel

       binding.name1Input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcViewModel.name1=s.toString()
            }
        })

        binding.name2Input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcViewModel.name2=s.toString()
            }
        })

        binding.buttonCalc.setOnClickListener { view: View ->
           calcViewModel.getResult()
        }


        calcViewModel.navigateToListView.observe(viewLifecycleOwner, Observer { bool ->
            bool?.let {
                this.findNavController().navigate(
                    CalculatorFragmentDirections.actionCalcFragmentToListFragment()
                )
            }
        })

        binding.buttonSave.setOnClickListener { view: View ->
            var result=calcViewModel.saveResult()
            if(result){
                calcViewModel.onSaveClicked(true)
            }
        }

        calcViewModel.properties.observe(viewLifecycleOwner, Observer { love ->
            love?.let {
               binding.resultText.text=love.percentage.toString()+"%"
                binding.resultView.text=love.result
            }
        })

        return binding.root
    }

}