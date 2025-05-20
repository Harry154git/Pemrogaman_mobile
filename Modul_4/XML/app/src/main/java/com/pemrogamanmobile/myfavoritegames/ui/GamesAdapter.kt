package com.pemrogamanmobile.myfavoritegames.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pemrogamanmobile.myfavoritegames.data.Games
import com.pemrogamanmobile.myfavoritegames.databinding.ItemGamesBinding
import timber.log.Timber

class GamesAdapter(
    private val onViewClicked: (Games) -> Unit,
    private val onDetailClicked: (Games) -> Unit
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private val items = mutableListOf<Games>()

    inner class ViewHolder(val binding: ItemGamesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGamesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {

            // tambah padding(entah knp di xmlnya gak mau nambah padding ke bawah, mau gak mau ya lewat sini yang ulun tau)
            val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.bottomMargin = 60
            root.layoutParams = layoutParams

            imageView.setImageResource(item.imageResourceId)
            titleText.text = holder.itemView.context.getString(item.titleResourceId)
            yearText.text = holder.itemView.context.getString(item.yearResourceId)
            detailText.text = holder.itemView.context.getString(item.detailResourceId)

            viewButton.setOnClickListener {
                val title = holder.itemView.context.getString(item.titleResourceId)
                Timber.d("View button clicked: $title")
                onViewClicked(item)
            }

            detailButton.setOnClickListener {
                val title = holder.itemView.context.getString(item.titleResourceId)
                Timber.d("Detail button clicked: $title")
                onDetailClicked(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<Games>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}