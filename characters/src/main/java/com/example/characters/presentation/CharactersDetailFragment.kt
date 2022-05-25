package com.example.characters.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.characters.databinding.FragmentDetailsBinding
import com.example.characters.di.CharactersComponentProvider
import com.example.characters.domain.model.CharacterDomain
import com.google.android.material.appbar.MaterialToolbar
import javax.inject.Inject

class CharactersDetailFragment : Fragment() {
    companion object {
        const val ARGUMENT: String = "characterId"
    }

    @Inject
   lateinit var viewModel: CharactersViewModel
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var textViewName: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewGender: TextView
    private lateinit var textViewCreated: TextView
    private lateinit var textViewSpecies: TextView
    private lateinit var image: ImageView
    private lateinit var appBar: MaterialToolbar

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
        appBar = binding.topAppBarDetail
        appBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestId: Int? = arguments?.getString(ARGUMENT)?.toInt()
        if (requestId != null) {
            viewModel.characters.observe(
                viewLifecycleOwner
            ) {
                containView(it.first { character -> character.id == requestId })
            }
        }

    }

    private fun containView(character: CharacterDomain) {
        textViewName.text = character.name
        appBar.title = character.name
        textViewStatus.text = character.status
        textViewSpecies.text = character.species
        textViewGender.text = character.gender
        textViewCreated.text = character.created.substring(0, 10)
        Glide.with(requireContext())
            .load(character.image)
            .into(image)
    }
}