package com.example.roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.databinding.FragmentMainScreenBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.w3c.dom.Entity

class MainScreenFragment : Fragment() {

    lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val groceryDAO = (requireActivity().application as GroceryApp).db.groceryDao()

        binding?.btnAdd?.setOnClickListener {
            addRecord(groceryDAO)
        }

        lifecycleScope.launch{
            groceryDAO.fetchAllGroceries().collect(){
                val list = ArrayList(it)
                setupListOfDataIntoRecyclerView(list, groceryDAO)
            }
        }
    }

    fun addRecord(groceryDAO: GroceryDAO) {
        val name = binding?.editTextItem?.text.toString()
        val quantity = binding?.editTextQuantity?.text.toString()

        if (name.isNotEmpty() && quantity.isNotEmpty()) {
            lifecycleScope.launch {
                groceryDAO.insert(ListEntity(name = name, quantity = quantity))
                Toast.makeText(context, "Record saved", Toast.LENGTH_LONG).show()
                binding?.editTextItem?.text?.clear()
                binding?.editTextQuantity?.text?.clear()
            }
        } else {
            Toast.makeText(context, "Name or Quantity can't be blank", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupListOfDataIntoRecyclerView(
        groceryList: ArrayList<ListEntity>,
        groceryDAO: GroceryDAO
    ) {
        if (groceryList.isNotEmpty()){
            val itemAdapter = ItemAdapter(groceryList )
            binding?.rvItemList?.layoutManager = LinearLayoutManager(context)
            binding?.rvItemList?.adapter = itemAdapter
            binding?.rvItemList?.visibility = View.VISIBLE
        }else{
            binding?.rvItemList?.visibility = View.GONE
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreenFragment()
    }
}