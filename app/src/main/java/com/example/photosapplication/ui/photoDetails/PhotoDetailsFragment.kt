package com.example.photosapplication.ui.photoDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.photosapplication.api.Config
import com.example.photosapplication.databinding.FragmentPhotoDetailsBinding
import com.example.photosapplication.models.Photo


class PhotoDetailsFragment : Fragment() {

    lateinit var binding: FragmentPhotoDetailsBinding
    lateinit var photo: Photo
    private val args: PhotoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = args.photo
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailsBinding.inflate(inflater)

        binding.apply {
            name.text = photo.name
            description.text = photo.description
            activity?.let {
                Glide.with(it)
                    .load(Config.getMediaUrl() + photo.image.name)
                    .into(photoImage)
            }
        }

        return binding.root
    }
}