package com.example.characters.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.characters.databinding.FragmentDetailsBinding
import com.example.characters.di.CharactersComponentProvider
import com.example.characters.domain.CharacterDomain
import com.google.android.material.appbar.MaterialToolbar
import javax.inject.Inject

class CharactersDetailFragment : Fragment() {
    @Inject
    lateinit var viewModel: CharactersViewModel
    lateinit var binding: FragmentDetailsBinding
    lateinit var textViewName: TextView
    lateinit var textViewStatus: TextView
    lateinit var textViewGender: TextView
    lateinit var textViewCreated: TextView
    lateinit var textViewSpecies: TextView
    lateinit var character: CharacterDomain
    lateinit var image: ImageView
    lateinit var appBar: MaterialToolbar
    var requestId = 1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CharactersComponentProvider).provideCharactersComponent()
            .injectCharactersDetailFragment(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        textViewCreated = binding.textViewDetailCreatedData
        textViewGender = binding.textViewDetailGenderData
        textViewName = binding.textViewDetailNameData
        textViewSpecies = binding.textViewDetailSpeciesData
        textViewStatus = binding.textViewDetailStatusData
        image = binding.imageViewCharacterDetail
        appBar=binding.topAppBarDetail
        appBar.setNavigationOnClickListener { requireActivity().onBackPressed()
            true }

        println("Fragment onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestId=arguments?.getString("characterId")!!.toInt()
        viewModel.characters.observe(
            viewLifecycleOwner,
            Observer { containView( it.first { character -> character.id == requestId }) })

    }

    private fun containView(character:CharacterDomain) {
        textViewName.text = character.name
        appBar.title=character.name
        textViewStatus.text = character.status
        textViewSpecies.text = character.species
        textViewGender.text = character.gender
        textViewCreated.text=character.created.substring(0,10)
        Glide.with(requireContext())
            .load(character.image)
            .into(image)
    }
}