package com.example.characters.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.databinding.CharactersFragmentBinding
import com.example.characters.di.CharactersComponentProvider
import java.util.*

import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModel: CharactersViewModel
    private lateinit var binding: CharactersFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar
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
        progressBar = binding.progressBar
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapter(requireContext(), viewModel) { id -> toItem(id) }
        recyclerView.adapter = adapter
        setContent(null)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                setContent(text)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                setContent(query)
                return false
            }
        })
    }

    private fun setContent(query: String?) {
        if (query == null) {
            viewModel.characters.observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    adapter.setList(it)
                    progressBar.setVisibility(View.INVISIBLE)
                }
            })
            viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
                progressBar.setVisibility(View.INVISIBLE)
                println("Error $it")
                Toast.makeText(requireContext(), "Error $it", LENGTH_LONG).show()
            })
        } else {
            viewModel.characters.observe(viewLifecycleOwner, Observer {
                adapter.setList(it.filter { character ->
                    character.name.lowercase(Locale.getDefault())
                        .contains(query.lowercase(Locale.getDefault()))
                })
            })
        }
    }

    private fun toItem(id: Int) {
        val uri = Uri.parse("android-app://characters/${id}")
        findNavController().navigate(uri)
    }

}