package com.example.inventory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.data.Item
import com.example.inventory.data.getFormattedPrice
import com.example.inventory.databinding.ItemListItemBinding

class ItemListAdapter(
   private val onItemClicked : (Item)->Unit
): ListAdapter<Item,ItemListAdapter.ItemListViewHolder>(DiffCallback){


    class ItemListViewHolder(private var binding: ItemListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Item){
            binding.apply {
                itemName.text = item.itemName
                itemPrice.text = item.getFormattedPrice()
                itemQuantity.text  =item.quantityInStock.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(ItemListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val current  = getItem(position)
        holder.itemView.setOnClickListener{
            onItemClicked(current)
        }
        holder.bind(current)
    }
    companion object {
        private val DiffCallback= object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return newItem ==oldItem
            }

        }
    }
}