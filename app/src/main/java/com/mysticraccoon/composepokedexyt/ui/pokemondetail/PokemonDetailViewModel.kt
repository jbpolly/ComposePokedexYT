package com.mysticraccoon.composepokedexyt.ui.pokemondetail

import androidx.lifecycle.ViewModel
import com.mysticraccoon.composepokedexyt.core.utils.Resource
import com.mysticraccoon.composepokedexyt.data.repository.PokemonRepository
import com.mysticraccoon.composepokedexyt.domain.responses.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        return repository.getPokemonInfo(pokemonName)
    }

}