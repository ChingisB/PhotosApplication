package com.example.photosapplication.ui.newPhotos

import android.content.Context
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
import com.example.photosapplication.databinding.FragmentNewPhotosBinding
import com.example.photosapplication.models.Photo
import com.example.photosapplication.util.Resource
import javax.inject.Inject


class NewPhotosFragment : Fragment() {


    lateinit var binding: FragmentNewPhotosBinding

    private lateinit var photoAdapter: PhotoAdapter

    private val viewModel: NewPhotosViewModel by viewModels { newPhotosViewModelFactory }

    @Inject
    lateinit var newPhotosViewModelFactory: NewPhotosViewModel.Factory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.injectNewPhotosFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPhotosBinding.inflate(inflater)


        setupRecyclerView()

        viewModel.photos.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.newPhotosErrorScreen.visibility = View.GONE
                    binding.swipeRefesh.isRefreshing = false
                    response.data?.let { photos ->
                        photoAdapter.submitList(photos.data)
                    }


                }
                is Resource.Loading -> {
                    binding.newPhotosErrorScreen.visibility = View.GONE
                    binding.swipeRefesh.isRefreshing = true

                }
                else -> {
                    binding.swipeRefesh.isRefreshing = false
                    binding.newPhotosErrorScreen.visibility = View.VISIBLE

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
        val onItemClickListener: (Photo) -> Unit = { photo ->
            val bundle = Bundle()
            bundle.apply {
                putSerializable("photo", photo)
            }
            navController.navigate(
                R.id.action_newPhotosFragment_to_photoDetailsFragment, bundle
            )

        }
        photoAdapter = PhotoAdapter(emptyList(), onItemClickListener)
        binding.apply {
            photoGrid.adapter = photoAdapter
            photoGrid.layoutManager = GridLayoutManager(activity, 2)
        }

    }


}