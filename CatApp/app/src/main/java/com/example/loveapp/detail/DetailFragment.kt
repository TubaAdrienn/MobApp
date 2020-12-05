package com.example.loveapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.loveapp.R
import com.example.loveapp.dao.LoveDatabase
import com.example.loveapp.databinding.DetailLayoutBinding
import kotlinx.android.synthetic.main.activity_main.*

class DetailFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: DetailLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.detail_layout, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = LoveDatabase.getInstance(application).loveDao
        val viewModelFactory = DetailViewModelFactory(dataSource, arguments.loveId)

        val detailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DetailViewModel::class.java)

        binding.detailViewModel = detailViewModel

        binding.setLifecycleOwner(this)

        return binding.root

    }

}