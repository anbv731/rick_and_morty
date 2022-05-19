package com.example.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.databinding.CharactersFragmentBinding

class CharactersFragment: Fragment() {
    private val viewModel: CharactersViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, CharactersViewModelFactory(activity.application))
            .get(CharactersViewModel::class.java)
    }
    lateinit var binding: CharactersFragmentBinding
    lateinit var recyclerView: RecyclerView
    //lateinit var viewModel: CharactersViewModel
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= CharactersFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root
        recyclerView = binding.recyclerView
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(
//            this,
//            CharactersViewModelFactory(requireContext())
//        ).get(CharactersViewModel::class.java)
        adapter = RecyclerAdapter(requireContext(), viewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        viewModel.characters.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            println("Error " + it.toString())
        })
        viewModel.getDBCharacters()
       // viewModel.getAllFish()
//        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { videos ->
//            videos?.apply {
//                viewModelAdapter?.videos = videos
//            }
//        })
    }
}