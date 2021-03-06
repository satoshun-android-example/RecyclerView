package com.github.satoshun.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.cardviews.CardViewsActivity
import com.github.satoshun.example.cardviewsviewgroup.CardViewsViewGroupActivity
import com.github.satoshun.example.constraintlayoutmatch.ConstraintMatchConstraintsActivity
import com.github.satoshun.example.databinding.AppActBinding
import com.github.satoshun.example.diff.DiffActivity
import com.github.satoshun.example.diffgroupie.DiffGroupieActivity
import com.github.satoshun.example.disblescroll.DisableScrollActivity
import com.github.satoshun.example.flexbox.FlexboxActivity
import com.github.satoshun.example.flexbox2.FlexboxActivity2
import com.github.satoshun.example.hasfixedsize.HasFixedSizeActivity
import com.github.satoshun.example.horizontalchipbind.HorizontalChipBindActivity
import com.github.satoshun.example.inconsistency.InconsistencyDetectActivity
import com.github.satoshun.example.itemtouchhelper.ItemTouchHelperActivity
import com.github.satoshun.example.mergeadapter.MergeAdapterActivity
import com.github.satoshun.example.mergeadapter.groupie.GroupieMergeAdapterActivity
import com.github.satoshun.example.merger.MergerActivity

class AppActivity : AppCompatActivity() {
  private lateinit var binding: AppActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = AppActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.hasFixedSize.setOnClickListener {
      startActivity(Intent(this, HasFixedSizeActivity::class.java))
    }

    binding.constraintMatchConstraints.setOnClickListener {
      startActivity(Intent(this, ConstraintMatchConstraintsActivity::class.java))
    }

    binding.horizontalBindChip.setOnClickListener {
      startActivity(Intent(this, HorizontalChipBindActivity::class.java))
    }

    binding.disableScroll.setOnClickListener {
      startActivity(Intent(this, DisableScrollActivity::class.java))
    }

    binding.merger.setOnClickListener {
      startActivity(Intent(this, MergerActivity::class.java))
    }

    binding.mergeAdapter.setOnClickListener {
      startActivity(Intent(this, MergeAdapterActivity::class.java))
    }

    binding.groupieMergeAdapter.setOnClickListener {
      startActivity(Intent(this, GroupieMergeAdapterActivity::class.java))
    }

    binding.main1.setOnClickListener {
      startActivity(Intent(this, FlexboxActivity::class.java))
    }

    binding.flexbox2.setOnClickListener {
      startActivity(Intent(this, FlexboxActivity2::class.java))
    }

    binding.main2.setOnClickListener {
      startActivity(Intent(this, StaggeredActivity::class.java))
    }

    binding.inconsistency.setOnClickListener {
      startActivity(Intent(this, InconsistencyDetectActivity::class.java))
    }

    binding.diff.setOnClickListener {
      startActivity(Intent(this, DiffActivity::class.java))
    }

    binding.diffGroupie.setOnClickListener {
      startActivity(Intent(this, DiffGroupieActivity::class.java))
    }

    binding.cardViews.setOnClickListener {
      startActivity(Intent(this, CardViewsActivity::class.java))
    }

    binding.cardViewsViewGroup.setOnClickListener {
      startActivity(Intent(this, CardViewsViewGroupActivity::class.java))
    }

    binding.touchHelper.setOnClickListener {
      startActivity(Intent(this, ItemTouchHelperActivity::class.java))
    }
  }
}
