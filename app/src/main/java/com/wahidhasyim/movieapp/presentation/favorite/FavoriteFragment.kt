package com.wahidhasyim.movieapp.presentation.favorite

import android.app.ActionBar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.FragmentFavoriteBinding
import com.wahidhasyim.movieapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteViewModelImpl, FragmentFavoriteBinding>(), FavoriteAdapter.Listener {

    private val viewModel: FavoriteViewModelImpl by viewModels() 
    private val adapter by lazy { FavoriteAdapter(this) }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    override fun getVM(): FavoriteViewModelImpl? = viewModel

    override fun getLayoutResource(): Int = R.layout.fragment_favorite

    override fun initViews() = with(binding) {
        rvFavorite.adapter = adapter
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewModel.movieFavoriteSource().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initData() {
        //Nothing to do here
    }

    override fun setupToolbar() = with(binding) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        toolbar.setNavigationIcon(R.drawable.round_arrow_back_24)
    }

    override fun onItemClick(item: MovieItem) {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteToDetailFragment(item))
    }
}