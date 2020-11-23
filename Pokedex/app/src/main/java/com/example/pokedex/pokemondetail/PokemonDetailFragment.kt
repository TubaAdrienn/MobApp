package com.example.pokedex.pokemondetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokedex.R
import com.example.pokedex.database.Pokemon
import com.example.pokedex.database.PokemonDatabase
import com.example.pokedex.databinding.DetailFragmentBinding

class PokemonDetailFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: DetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = PokemonDetailFragmentArgs.fromBundle(arguments)

        // Create an instance of the ViewModel Factory.
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabase
        val viewModelFactory = PokemonDetailViewModelFactory(arguments.pokeKey, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val pokemonDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(PokemonDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.detailViewModel = pokemonDetailViewModel

        binding.pokemon=pokemonDetailViewModel.getPokemon()

        binding.setLifecycleOwner(this)

        return binding.root
    }

}