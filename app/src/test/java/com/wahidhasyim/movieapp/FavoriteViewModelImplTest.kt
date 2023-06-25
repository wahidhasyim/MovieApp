package com.wahidhasyim.movieapp

import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import com.wahidhasyim.movieapp.presentation.favorite.FavoriteViewModelImpl
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@ExperimentalCoroutinesApi
object FavoriteViewModelImplSpek : Spek({

    val mockUseCase by memoized { mockk<MovieUseCase>() }
    val viewModel by memoized { FavoriteViewModelImpl(mockUseCase) }

    Feature("FavoriteViewModelImpl") {

        Scenario("Retrieve favorite movies") {

            When("Retrieving favorite movies") {
                runBlockingTest {
                    viewModel.movieFavoriteSource()
                }
            }

            Then("Favorite movies should be emitted correctly") {
                runBlockingTest {
                    mockUseCase.favoriteMovie()
                }
            }
        }
    }
})
