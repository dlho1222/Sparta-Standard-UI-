package com.example.youtubeui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.databinding.ItemSponsorBinding
import com.example.youtubeui.databinding.ItemVodBinding

class ImageAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_SPONSOR = 0
        const val ITEM_VOD = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position %3 == 0) ITEM_SPONSOR else ITEM_VOD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when (viewType) {
            ITEM_SPONSOR -> {
                val binding = ItemSponsorBinding.inflate(inflater, parent, false)
                SponsorViewHolder(binding)
            }

            else -> {
                val binding = ItemVodBinding.inflate(inflater, parent, false)
                VodViewHolder(binding)
            }

        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SponsorViewHolder -> {
                holder.bind(onClickListener)
            }

            is VodViewHolder -> {
                holder.bind(onClickListener)
            }
        }
    }
}

class SponsorViewHolder(private val binding: ItemSponsorBinding) :
    RecyclerView.ViewHolder(binding.root) {
            fun bind(onClickListener: OnClickListener){
                binding.ivSpeaker.setOnClickListener {
                    onClickListener.onSpeakerClicked()
                }
                binding.ivSubTitle.setOnClickListener {
                    onClickListener.onSpeakerClicked()
                }
            }
}

class VodViewHolder(private val binding: ItemVodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(onClickListener: OnClickListener){
        binding.ivSpeaker.setOnClickListener {
            onClickListener.onSpeakerClicked()
        }
        binding.ivSubTitle.setOnClickListener {
            onClickListener.onSpeakerClicked()
        }
    }
}
interface OnClickListener{
    fun onSpeakerClicked()
    fun onSubTitleClicked()
}