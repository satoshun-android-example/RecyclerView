package com.github.satoshun.example.mergeadapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItemBinding

class MergeAdapterActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager
    val mergeAdapter = MergeAdapter()
    mergeAdapter.addAdapter(ChipItemAdapter(listOf("hoge", "test")))
    mergeAdapter.addAdapter(ChipItemAdapter2().apply { submitList(listOf("fuga", "TOM")) })
    binding.recycler.adapter = mergeAdapter
  }
}

class ChipItemAdapter(items: List<String>) :
  ListViewTypeAdapter<String>(items, R.layout.main_item) {
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: String, position: Int) {
    val binding = MainItemBinding.bind(holder.itemView)
    binding.chip.text = item
  }
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
  override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }
}

class ChipItemAdapter2 : ItemAdapter<String>(R.layout.main_item, DIFF_CALLBACK) {
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: String, position: Int) {
    val binding = MainItemBinding.bind(holder.itemView)
    binding.chip.text = item
  }
}

abstract class ListViewTypeAdapter<T>(
  private val items: List<T>,
  @LayoutRes private val layoutId: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return object : RecyclerView.ViewHolder(
      LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {}
  }

  override fun getItemViewType(position: Int): Int = layoutId
  override fun getItemCount(): Int = items.count()

  final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    onBindViewHolder(holder, items[position], position)
  }

  abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: T, position: Int)
}

abstract class ItemAdapter<T>(
  @LayoutRes private val layoutId: Int,
  diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {
  final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return object : RecyclerView.ViewHolder(
      LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {}
  }

  final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    onBindViewHolder(holder, getItem(position), position)
  }

  abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: T, position: Int)
}
