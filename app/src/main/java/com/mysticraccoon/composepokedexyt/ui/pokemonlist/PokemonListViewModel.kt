package com.mysticraccoon.composepokedexyt.ui.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.mysticraccoon.composepokedexyt.core.utils.Constants.PAGE_SIZE
import com.mysticraccoon.composepokedexyt.core.utils.Resource
import com.mysticraccoon.composepokedexyt.data.models.PokedexListEntry
import com.mysticraccoon.composepokedexyt.data.repository.PokemonRepository
import com.mysticraccoon.composepokedexyt.domain.responses.PokemonResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf(listOf<PokedexListEntry>())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String){
        val listToSearch = if(isSearchStarting){
            pokemonList.value
        }else{
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                //were searching something and then erased the text
                //display initial pokemon list again
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }else{
                val results = listToSearch.filter {
                    it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                            it.number.toString() == query.trim()
                }
                if(isSearchStarting){
                    cachedPokemonList = pokemonList.value
                    isSearchStarting = false
                }
                pokemonList.value = results
                isSearching.value = true
            }
        }

    }

    fun calcDominantColor(drawable: Drawable, onFinished: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinished(Color(colorValue))
            }
        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(PAGE_SIZE, currentPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    result.data?.let { list ->
                        endReached.value = currentPage * PAGE_SIZE >= list.count
                        val pokedexEntries =
                            list.pokemonResults.mapIndexed { index, pokemonEntry ->
                                val pokemonNumber = if (pokemonEntry.url.endsWith("/")) {
                                    pokemonEntry.url.dropLast(1).takeLastWhile { it.isDigit() }
                                } else {
                                    pokemonEntry.url.takeLastWhile { it.isDigit() }
                                }.toInt()
                                val imageUrl =
                                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumber}.png"
                                PokedexListEntry(
                                    pokemonName = pokemonEntry.name.replaceFirstChar { it.uppercase() },
                                    imageUrl = imageUrl,
                                    number = pokemonNumber
                                )
                            }
                        currentPage++
                        loadError.value = ""
                        isLoading.value = false
                        pokemonList.value += pokedexEntries
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message.orEmpty()
                    isLoading.value = false
                }
            }
        }
    }

}