package com.wahidhasyim.movieapp.presentation.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wahidhasyim.movieapp.BuildConfig
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.FragmentDetailBinding
import com.wahidhasyim.movieapp.util.BaseFragment
import com.wahidhasyim.movieapp.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModelImpl, FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModelImpl by viewModels()
    private val adapter by lazy { ReviewAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun getVM(): DetailViewModelImpl = viewModel

    override fun getLayoutResource(): Int = R.layout.fragment_detail

    override fun initViews() = with(binding) {
        rvReviews.adapter = adapter

        btnFavorite.setOnClickListener {
            viewModel.addFavoriteMovie(args.MovieItem)
        }
        btnRemoveFavorite.setOnClickListener {
            viewModel.deleteFavoriteMovie(args.MovieItem)
        }
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    args.MovieItem.title
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Share via")
            startActivity(shareIntent)
        }
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewModel.reviewSource(args.MovieItem.id.toLong()).collectLatest {
                adapter.submitData(it)
            }
        }

        viewModel.isFavoriteMovie().observe(viewLifecycleOwner) {
            if (it) {
                binding.btnFavorite.visibility = View.INVISIBLE
                binding.btnRemoveFavorite.visibility = View.VISIBLE
            } else {
                binding.btnRemoveFavorite.visibility = View.INVISIBLE
                binding.btnFavorite.visibility = View.VISIBLE
            }
        }
    }

    override fun initData() = with(binding) {
        args.MovieItem.let { movie ->
            viewModel.loadData(movie.id.toLong())
            actvTitle.text = movie.title
            acivBanner.loadImage(BuildConfig.BASE_URL_IMG+movie.backdropPath)
            actvDescription.text = movie.overview
        }
    }

    override fun setupToolbar() = with(binding) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        toolbar.setNavigationIcon(R.drawable.round_arrow_back_24)
        toolbar.title = args.MovieItem.title
    }

}