package com.github.satoshun.example.itemtouchhelper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.ItemTouchHelperActBinding
import com.github.satoshun.example.databinding.TextViewItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class ItemTouchHelperActivity : AppCompatActivity() {
  private lateinit var binding: ItemTouchHelperActBinding
  private lateinit var touchHelper: ItemTouchHelper

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ItemTouchHelperActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    val items = (0..100).map {
      TextViewItem {
        touchHelper.startDrag(it)
      }
    }.toMutableList()
    val adapter = GroupAdapter<GroupieViewHolder>().apply {
      addAll(items)
    }
    binding.recycler.adapter = adapter

    touchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
      override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)
      }

      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        items.swap(viewHolder.adapterPosition, target.adapterPosition)
        adapter.update(items)
        return true
      }

      override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
          viewHolder?.itemView?.setBackgroundColor(Color.BLUE)
        }
      }

      override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.setBackgroundColor(0)
      }

      override fun isLongPressDragEnabled(): Boolean {
        return false
      }

      override fun isItemViewSwipeEnabled(): Boolean {
        return false
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
      }
    })
    touchHelper.attachToRecyclerView(binding.recycler)
  }
}

class TextViewItem(val callback: (GroupieViewHolder) -> Unit) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.text_view_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    val binding = TextViewItemBinding.bind(viewHolder.itemView)
    binding.title.text = "$position"
    binding.button.setOnLongClickListener {
      callback(viewHolder)
      true
    }
  }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
  val tmp = this[index1]
  this[index1] = this[index2]
  this[index2] = tmp
}
