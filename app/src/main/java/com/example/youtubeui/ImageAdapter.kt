package com.example.youtubeui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.databinding.ItemSponsorBinding
import com.example.youtubeui.databinding.ItemVod2Binding
import com.example.youtubeui.databinding.ItemVodBinding

class ImageAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_SPONSOR = 0
        const val ITEM_VOD = 1
        const val ITEM_VOD2 = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ITEM_SPONSOR else if (position % 2 == 0) ITEM_VOD else ITEM_VOD2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when (viewType) {
            ITEM_SPONSOR -> {
                val binding = ItemSponsorBinding.inflate(inflater, parent, false)
                SponsorViewHolder(binding)
            }

            ITEM_VOD -> {
                val binding = ItemVodBinding.inflate(inflater, parent, false)
                VodViewHolder(binding)
            }

            else -> {
                val binding = ItemVod2Binding.inflate(inflater, parent, false)
                Vod2ViewHolder(binding)
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

            is Vod2ViewHolder -> {
                holder.bind(onClickListener)
            }
        }
    }
}

class SponsorViewHolder(private val binding: ItemSponsorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var speakerState = false
    var subState = false
    fun bind(onClickListener: OnClickListener) {
        binding.ivSpeaker.setOnClickListener {
            onClickListener.onSpeakerClicked()
            speakerState = !speakerState
            if (speakerState) binding.ivSpeaker.setImageResource(R.drawable.speaker_on) else binding.ivSpeaker.setImageResource(
                R.drawable.speaker_off
            )
        }
        binding.ivSubTitle.setOnClickListener {
            onClickListener.onSubTitleClicked()
            subState = !subState
            if (subState) binding.ivSubTitle.setImageResource(R.drawable.subtitle_on) else binding.ivSubTitle.setImageResource(
                R.drawable.subtitle_off
            )
        }
        binding.ivSponsor.setOnClickListener {
            onClickListener.onVodClicked()
        }
    }
}

class VodViewHolder(private val binding: ItemVodBinding) : RecyclerView.ViewHolder(binding.root) {
    var speakerState = false
    var subState = false
    fun bind(onClickListener: OnClickListener) {
        binding.ivSpeaker.setOnClickListener {
            onClickListener.onSpeakerClicked()
            speakerState = !speakerState
            if (speakerState) binding.ivSpeaker.setImageResource(R.drawable.speaker_on) else binding.ivSpeaker.setImageResource(
                R.drawable.speaker_off
            )
        }
        binding.ivSubTitle.setOnClickListener {
            onClickListener.onSubTitleClicked()
            subState = !subState
            if (subState) binding.ivSubTitle.setImageResource(R.drawable.subtitle_on) else binding.ivSubTitle.setImageResource(
                R.drawable.subtitle_off
            )
        }
        binding.ivVod.setOnClickListener {
            onClickListener.onVodClicked()
        }
    }
}

class Vod2ViewHolder(private val binding: ItemVod2Binding) : RecyclerView.ViewHolder(binding.root) {
    var speakerState = false
    var subState = false
    fun bind(onClickListener: OnClickListener) {
        binding.ivSpeaker.setOnClickListener {
            onClickListener.onSpeakerClicked()
            speakerState = !speakerState
            if (speakerState) binding.ivSpeaker.setImageResource(R.drawable.speaker_on) else binding.ivSpeaker.setImageResource(
                R.drawable.speaker_off
            )
        }
        binding.ivSubTitle.setOnClickListener {
            onClickListener.onSubTitleClicked()
            subState = !subState
            if (subState) binding.ivSubTitle.setImageResource(R.drawable.subtitle_on) else binding.ivSubTitle.setImageResource(
                R.drawable.subtitle_off
            )
        }
        binding.ivVod2.setOnClickListener {
            onClickListener.onVodClicked()
        }
    }
}

interface OnClickListener {
    fun onSpeakerClicked()
    fun onSubTitleClicked()
    fun onVodClicked()
}