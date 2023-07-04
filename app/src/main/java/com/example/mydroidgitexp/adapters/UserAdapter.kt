package com.example.mydroidgitexp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.User
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.databinding.ItemUserBinding
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import timber.log.Timber

class UserAdapter(private val onUserClickListener: OnUserClickListener) : BindingListAdapter<User, UserAdapter.UserViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        parent.binding<ItemUserBinding>(R.layout.item_user).let(::UserViewHolder)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bindUser(getItem(position))

    inner class UserViewHolder constructor(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Timber.d("Clicked at: $onClickedAt")
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    onUserClickListener.openUserInfo(user)
                }
            }
        }

        fun bindUser(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}

interface OnUserClickListener {
    fun openUserInfo(user: User)
}