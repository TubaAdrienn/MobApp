package com.example.loveapp.lovelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loveapp.R
import com.example.loveapp.dao.LoveDatabase
import com.example.loveapp.databinding.ListFragmentBinding

class LoveListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: ListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.list_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = LoveDatabase.getInstance(application).loveDao
        val viewModelFactory = LoveListViewModelFactory(dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val loveListViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LoveListViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.loveListViewModel = loveListViewModel


        val adapter = LoveListAdapter(LoveListener { id ->
            println("lala")
            loveListViewModel.onLoveResultClicked(id)
        })

        binding.loveList.adapter = adapter

        loveListViewModel.results.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        loveListViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
                this.findNavController().navigate(
                    LoveListFragmentDirections.actionListFragmentToDetailFragment(id)
                )
                loveListViewModel.onDetailNavigated()
            }
        })

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                when (position) {
                    0 -> 3
                    else -> 1
                }
        }
        binding.loveList.layoutManager = manager

        return binding.root
    }

}