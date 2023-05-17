package com.example.photosapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photosapplication.R
import com.example.photosapplication.api.Config
import com.example.photosapplication.databinding.PhotoItemBinding
import com.example.photosapplication.models.Photo

class PhotoAdapter(
    private var photoList: List<Photo>,
    private var onItemClickListener: (Photo) -> Unit
) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {


    inner class PhotoViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = PhotoItemBinding.bind(view)
        fun bind(photo: Photo) {
            binding.apply {
                root.setOnClickListener { onItemClickListener.invoke(photo) }
                Glide.with(view.context)
                    .load(Config.getMediaUrl() + photo.image.name)
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)

        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(photos: List<Photo>) {
        this.photoList = photos
        notifyDataSetChanged()
    }
}