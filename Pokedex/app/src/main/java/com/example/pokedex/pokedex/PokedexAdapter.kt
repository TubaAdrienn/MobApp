package com.example.pokedex.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.database.Pokemon
import com.example.pokedex.databinding.PokemonItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexAdapter(val clickListener: PokemonListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(PokemonDiffCallback()), Filterable {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    private val fullList = ArrayList<Pokemon>()

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    fun addHeaderAndSubmitList(list: List<Pokemon>) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.PokemonItem(it) }
            }
            if(fullList.isEmpty()){
                fullList.addAll(list)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position) as DataItem.PokemonItem
                holder.bind(item.pokemon, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_ITEM
        }
    }



    class ViewHolder private constructor(val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon, clickListener: PokemonListener) {
            binding.pokemon = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PokemonItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Pokemon>()
                if (constraint == null || constraint.length == 0) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()

                    for(pokemon : Pokemon in fullList){
                        if(pokemon.name.toLowerCase().contains(filterPattern))
                            filteredList.add(pokemon)
                    }
                }
                val result=FilterResults()
                result.values=filteredList
                return result
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                addHeaderAndSubmitList(results?.values as ArrayList<Pokemon>)
            }

        }
    }
}


class PokemonDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }


}

class PokemonListener(val clickListener: (pokeId: Long) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.pokeId)
}

sealed class DataItem {
    abstract val id: Long

    data class PokemonItem(val pokemon: Pokemon) : DataItem() {
        override val id = pokemon.pokeId
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}
