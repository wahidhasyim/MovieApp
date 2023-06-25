package com.wahidhasyim.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.FragmentHomeBinding
import com.wahidhasyim.movieapp.presentation.MovieAdapter
import com.wahidhasyim.movieapp.presentation.MoviePopularAdapter
import com.wahidhasyim.movieapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<HomeViewModelImpl, FragmentHomeBinding>(), MoviePopularAdapter.Listener, MovieAdapter.Listener  {

    private val viewModel: HomeViewModelImpl by viewModels()
    private val popularAdapter by lazy { MoviePopularAdapter(this) }
    private val topRatedAdapter by lazy { MovieAdapter(this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(this) }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun getVM(): HomeViewModelImpl = viewModel

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initViews() {
        with(binding) {
            val appBarConfiguration = AppBarConfiguration(findNavController().graph)
            toolbar.setupWithNavController(findNavController(), appBarConfiguration)

            rvPopular.adapter = popularAdapter
            rvNowPlaying.adapter = nowPlayingAdapter
            rvTopRated.adapter = topRatedAdapter
        }
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewModel.movieSource(0).collectLatest { popularAdapter.submitData(it) }
        }
        lifecycleScope.launch {
            viewModel.movieSource(1).collectLatest { topRatedAdapter.submitData(it) }
        }
        lifecycleScope.launch {
            viewModel.movieSource(2).collectLatest { nowPlayingAdapter.submitData(it) }
        }
    }

    override fun initData() {
        //Nothing to do here
    }

    override fun setupToolbar() {
        binding.toolbar.inflateMenu(R.menu.menu_main)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_favorite -> {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                    )
                }
            }
            true
        }
    }

    override fun onItemClick(item: MovieItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item))
    }
}