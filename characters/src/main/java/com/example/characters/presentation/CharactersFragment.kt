package com.example.characters.presentation

import android.content.Context
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
import com.example.characters.di.CharactersComponent
import com.example.characters.di.DaggerCharactersComponent
import com.example.characters.di.MyApplication
import javax.inject.Inject

class CharactersFragment: Fragment() {

    @Inject
    lateinit var viewModel:CharactersViewModel
//    private val viewModel: CharactersViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onActivityCreated()"
//        }
//        ViewModelProvider(this, CharactersViewModelFactory(activity.application))
//            .get(CharactersViewModel::class.java)
//    }
    lateinit var binding: CharactersFragmentBinding
    lateinit var recyclerView: RecyclerView
    //lateinit var viewModel: CharactersViewModel
    lateinit var adapter: RecyclerAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MyApplication ).charactersComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= CharactersFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root
        recyclerView = binding.recyclerView
        println("Fragment onCreateView")
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
        println("Fragment onViewCreated")
        viewModel.getDBCharacters()


        viewModel.characters.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            println("Error " + it.toString())
        })

       // viewModel.getAllFish()
//        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { videos ->
//            videos?.apply {
//                viewModelAdapter?.videos = videos
//            }
//        })
    }
}