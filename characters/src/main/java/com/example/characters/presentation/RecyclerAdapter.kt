package com.example.characters.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characters.databinding.ItemBinding
import com.example.characters.domain.CharacterDomain
import javax.security.auth.callback.Callback


class RecyclerAdapter(private val context: Context, private val viewModel: CharactersViewModel,private val toItem:(id: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.CharactersViewHolder>() {
    var characters = mutableListOf<CharacterDomain>()

    fun setList(list: List<CharacterDomain>) {
        this.characters = list.toMutableList()
       notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = characters[position]

        holder.textViewName.text = (character.name)
        Glide.with(context)
            .load(character.image)
            .circleCrop()
            .into(holder.imageView)
        holder.cardView.setOnClickListener{println("Click card view "+ character.id)
toItem.invoke(character.id)

//            val navHostFragment =
//                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//            val navController = navHostFragment.navController
           //val action =CharactersFragmentDirections
//                SpecifyAmountFragmentDirections
//                    .actionSpecifyAmountFragmentToConfirmationFragment()
//            view.findNavController().navigate(action)
        }
//        holder.addButton.setOnClickListener {
//            viewModel.saveToFavourite(fish)
//            notifyDataSetChanged()
//        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class CharactersViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewName = binding.nameTextView
        val imageView = binding.imageView
        val cardView=binding.CardViewId
    }
}