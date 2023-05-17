package com.example.photosapplication.ui.popularPhotos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photosapplication.MainActivity
import com.example.photosapplication.R
import com.example.photosapplication.adapters.PhotoAdapter
import com.example.photosapplication.databinding.FragmentPopularPhotosBinding
import com.example.photosapplication.models.Photo
import com.example.photosapplication.util.Resource
import javax.inject.Inject


class PopularPhotosFragment : Fragment() {

    lateinit var binding: FragmentPopularPhotosBinding


    private val viewModel: PopularPhotosViewModel by viewModels {
        popularPhotosViewModelFactory
    }


    private lateinit var photoAdapter: PhotoAdapter

    @Inject
    lateinit var popularPhotosViewModelFactory: PopularPhotosViewModel.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).appComponent.injectPopularPhotosFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularPhotosBinding.inflate(inflater)


        setupRecyclerView()

        viewModel.photos.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.swipeRefesh.isRefreshing = false
                    binding.popularPhotosErrorScreen.visibility = View.GONE
                    response.data?.let { photos ->
                        photoAdapter.submitList(photos.data)
                    }

                }
                is Resource.Loading -> {
                    binding.swipeRefesh.isRefreshing = true
                    binding.popularPhotosErrorScreen.visibility = View.GONE
                }
                else -> {
                    binding.swipeRefesh.isRefreshing = false
                    binding.popularPhotosErrorScreen.visibility = View.VISIBLE
                }
            }
        }

        binding.swipeRefesh.setOnRefreshListener {
            viewModel.getPhotos()
        }


        return binding.root
    }

    private fun setupRecyclerView() {
        val navController = findNavController()
        val onItemClickListener: (Photo) -> Unit =
            { photo ->
                val bundle = Bundle()
                bundle.apply {
                    putSerializable("photo", photo)
                }
                navController.navigate(
                    R.id.action_populartPhotosFragment_to_photoDetailsFragment,
                    bundle
                )

            }
        photoAdapter = PhotoAdapter(emptyList(), onItemClickListener)
        binding.apply {
            photoGrid.adapter = photoAdapter
            photoGrid.layoutManager = GridLayoutManager(activity, 2)
        }
    }
}