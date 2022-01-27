package com.example.birthdaysapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.birthdaysapp.R
import com.example.birthdaysapp.data.models.Birthday
import com.example.birthdaysapp.databinding.ItemBirthdayBinding
import com.example.birthdaysapp.ui.HomeFragment
import com.example.birthdaysapp.ui.activities.MainActivity
import javax.inject.Inject


class BirthdaysAdapter @Inject constructor() : RecyclerView.Adapter<BirthdaysAdapter.BirthdaysViewHolder>() {
    inner class BirthdaysViewHolder(val binding: ItemBirthdayBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Birthday>() {
        override fun areItemsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
            return oldItem.name.first == newItem.name.first &&
                    oldItem.name.last == newItem.name.last
        }

        override fun areContentsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var birthdays: List<Birthday>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = birthdays.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdaysViewHolder {
        return BirthdaysViewHolder(ItemBirthdayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    private lateinit var fragment: Fragment
    fun setParent(fragment: Fragment){
        this.fragment = fragment
    }

    override fun onBindViewHolder(holder: BirthdaysViewHolder, position: Int) {
        holder.binding.apply {
            val bd = birthdays[position]
            profile.avatarInitials = bd.name.first[0]+""+bd.name.last[0]
            name.text = String.format(fragment.getString(R.string.name), bd.name.first ,  bd.name.last)
            birthday.text = bd.dob.date.substring(0,10)
            holder.itemView.setOnClickListener {
                (fragment as HomeFragment).goToDetails(bd)
            }
        }
    }
}