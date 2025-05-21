package com.pemrogamanmobile.myfavoritegames.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pemrogamanmobile.myfavoritegames.data.Games
import com.pemrogamanmobile.myfavoritegames.databinding.ItemGamesBinding

class GamesAdapter(
    private val items: List<Games>,
    private val onDetailClick: (Games) -> Unit
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemGamesBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGamesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {

            val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.bottomMargin = 60
            root.layoutParams = layoutParams

            imageView.setImageResource(item.imageResourceId)
            titleText.text   = holder.itemView.context.getString(item.titleResourceId)
            yearText.text    = holder.itemView.context.getString(item.yearResourceId)
            detailText.text  = holder.itemView.context.getString(item.detailResourceId)

            viewButton.setOnClickListener {
                val url = holder.itemView.context.getString(item.steamLinkResourceId)
                holder.itemView.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(url))
                )
            }
            detailButton.setOnClickListener {
                onDetailClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}