package com.example.classwork9.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork9.R
import com.example.classwork9.databinding.Item2Binding
import com.example.classwork9.databinding.ItemBinding

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = (0..11).toList()

    lateinit var listener: (Int) -> Unit
    lateinit var backSpaceListener: () -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ViewHolder1(ItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
        return ViewHolder2(Item2Binding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder2)
            holder.bind(position)
        else if (holder is ViewHolder1)
            holder.bind(position)
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        if (position == 9 || position == 11)
            return 2
        return 1
    }

    inner class ViewHolder1(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var num = 0
            if (position == 10)
                binding.num.text = "0"
            else {
                binding.num.text = (position + 1).toString()
                num = position + 1
            }
            binding.dot4.setOnClickListener {
                listener(num)
            }
        }
    }

    inner class ViewHolder2(private val binding: Item2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            if (position == 11) {
                binding.icon.setImageResource(R.drawable.ic_baseline_backspace_24)
                binding.dot4.setOnClickListener {
                    backSpaceListener()
                }
            } else
                binding.icon.setImageResource(R.drawable.ic_baseline_fingerprint_24)
        }
    }


}
