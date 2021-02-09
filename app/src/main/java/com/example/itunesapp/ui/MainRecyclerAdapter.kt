package com.example.itunesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunesapp.R
import com.example.itunesapp.room.ArtistEntity

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {
    inner class MainViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private val oldData = ArrayList<ArtistEntity>()

    class HomeDiffCallBack(
        private val oldList: List<ArtistEntity>,
        private val newList: List<ArtistEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition].trackId == oldList[oldItemPosition].trackId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList === oldList

    }

    fun setData(newList: List<ArtistEntity>) {
        val diffCallBack = HomeDiffCallBack(oldData, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        oldData.clear()
        oldData.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artist_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val artistProfile = holder.view.findViewById<ImageView>(R.id.artistProfile)
        val artistName = holder.view.findViewById<TextView>(R.id.artistName)
        val trackName = holder.view.findViewById<TextView>(R.id.trackName)
        Glide.with(holder.view).load(oldData[position].photoUrl).into(artistProfile)
        artistName.text=oldData[position].artistName
        trackName.text=oldData[position].trackName
    }

    override fun getItemCount() = oldData.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}