package com.example.roomexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.databinding.ItemsRowBinding

class ItemAdapter(private var items: ArrayList<ListEntity>,
                  //private var updateListener:(id:Int)->Unit,
                  //private var deleteListener:(id:Int)->Unit


): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root){
        val llMain = binding.llMain
        val name = binding.textName
        val quantity = binding.textQuantity
        val ivEdit = binding.ivEdit
        val ivDelete = binding.ivDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

        holder.name.text = item.name
        holder.quantity.text = item.quantity

        if (position % 2 == 0){
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
            R.color.colorLightGreen))
        }else{
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.colorLightYellow))
        }

        holder.ivEdit.setOnClickListener {
            //updateListener.invoke(item.id)
        }

        holder.ivDelete.setOnClickListener {
            //deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}