package com.example.characters.presentation

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.databinding.CharactersFragmentBinding
import com.example.characters.di.CharactersComponentProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModel: CharactersViewModel

    //    private val viewModel: CharactersViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onActivityCreated()"
//        }
//        ViewModelProvider(this, CharactersViewModelFactory(activity.application))
//            .get(CharactersViewModel::class.java)
//    }
    lateinit var binding: CharactersFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var adapter: RecyclerAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CharactersComponentProvider).provideCharactersComponent()
            .injectCharactersFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.recyclerCharacters
        searchView = binding.searchViewId

        println("Fragment onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(
//            this,
//            CharactersViewModelFactory(requireContext())
//        ).get(CharactersViewModel::class.java)
        adapter = RecyclerAdapter(requireContext(), viewModel, {id -> toItem(id)})
        recyclerView.adapter = adapter
        println("Fragment onViewCreated")
        //viewModel.getDBCharacters()
        setViewModel(null)



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                setViewModel(text)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                setViewModel(query)
                return false
            }
        })
    }

    private fun setViewModel(query: String?) {
        if (query == null) {
            viewModel.characters.observe(viewLifecycleOwner, Observer {
                adapter.setList(it)
            })
            viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
                println("Error " + it.toString())
            })
        }else{
            viewModel.characters.observe(viewLifecycleOwner, Observer {
                adapter.setList(it.filter { character->character.name.lowercase(Locale.getDefault())
                    .contains(query.lowercase(Locale.getDefault())) })
            })
        }
    }
    fun toItem(id:Int){
        val uri= Uri.parse("android-app://characters/${id}")
        findNavController().navigate(uri)
    }

}