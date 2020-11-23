package com.example.pokedex.pokedex


import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Adapter
import android.widget.Filter
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.database.Pokemon
import com.example.pokedex.database.PokemonDatabase
import com.example.pokedex.databinding.PokedexFragmentBinding
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.title_fragment.view.*

class PokedexFragment : Fragment() {

    lateinit var adapter: PokedexAdapter
    private var pokemons = ArrayList<Pokemon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: PokedexFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.pokedex_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabase
        val viewModelFactory = PokedexViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val pokedexViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(PokedexViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.pokedexViewModel = pokedexViewModel

        val adapter = PokedexAdapter(PokemonListener { pokeid ->
            pokedexViewModel.onPokemonClicked(pokeid)
        })

        binding.pokemonList.adapter = adapter

        pokedexViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { poke ->
            poke?.let {
                this.findNavController().navigate(
                    PokedexFragmentDirections.actionPokedexFragmentToPokedexDetail(poke)
                )
                pokedexViewModel.onPokemonDetailNavigated()
            }
        })

        pokemons = arrayListOf<Pokemon>(
            pokedexViewModel.mewtwo,
            pokedexViewModel.pikachu,
            pokedexViewModel.charm,
            pokedexViewModel.chari
        )

        adapter.addHeaderAndSubmitList(pokemons)

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                when (position) {
                    0 -> 3
                    else -> 1
                }
        }
        binding.pokemonList.layoutManager = manager



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                if (searchView.text==null)
                    adapter.addHeaderAndSubmitList(pokemons)
                return false
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }
}