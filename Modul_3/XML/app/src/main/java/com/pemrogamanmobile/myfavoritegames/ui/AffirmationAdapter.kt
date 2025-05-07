package com.pemrogamanmobile.myfavoritegames.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pemrogamanmobile.myfavoritegames.data.Affirmation
import com.pemrogamanmobile.myfavoritegames.databinding.ItemAffirmationBinding

class AffirmationAdapter(
    private val items: List<Affirmation>,
    private val onDetailClick: (Affirmation) -> Unit
) : RecyclerView.Adapter<AffirmationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAffirmationBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAffirmationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
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