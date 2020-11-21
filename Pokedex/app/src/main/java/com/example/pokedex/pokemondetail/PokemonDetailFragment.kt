package com.example.pokedex.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.databinding.DetailFragmentBinding

class PokemonDetailFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(inflater, R.layout.detail_fragment, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
}