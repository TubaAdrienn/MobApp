package com.example.pokedex.titlefragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokedex.R
import com.example.pokedex.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<TitleFragmentBinding>(inflater, R.layout.title_fragment, container, false)
        binding.buttonPokedex.setOnClickListener { view: View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToPokedexFragment())
        }
        binding.buttonFav.setOnClickListener {view: View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToFavouriteFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}
