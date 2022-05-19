package com.example.characters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characters.databinding.ItemBinding
import com.example.domain.CharacterDomain


class RecyclerAdapter(private val context: Context, private val viewModel: CharactersViewModel) :
    RecyclerView.Adapter<RecyclerAdapter.CharactersViewHolder>() {
    var characters = mutableListOf<CharacterDomain>()

    fun setList(list: List<CharacterDomain>) {
        this.characters = list.toMutableList()
//        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = characters[position]

        holder.textViewName.text = (character.name)
//        Glide.with(context)
//            .load(character.imageGallery?.src)
//            .into(holder.imageView)
//        holder.addButton.setOnClickListener {
//            viewModel.saveToFavourite(fish)
//            notifyDataSetChanged()
//        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class CharactersViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewName = binding.name
        val imageView = binding.image
    }
}