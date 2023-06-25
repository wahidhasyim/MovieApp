package com.wahidhasyim.movieapp

import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import com.wahidhasyim.movieapp.presentation.detail.DetailViewModelImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@ExperimentalCoroutinesApi
class DetailViewModelImplTestSpek : Spek({

    val mockUseCase by memoized { mockk<MovieUseCase>() }
    val viewModel by memoized { DetailViewModelImpl(mockUseCase) }

    Feature("DetailViewModelImpl") {
        val dummyMovieId = 123L
        val dummyMovieItem = MovieItem(false, "", 123, "", "", "", 0.0, "", "", "", false, 0.0, 3)

        Scenario("Retrieve review source") {

            When("Retrieving review source") {
                runBlockingTest {
                    viewModel.reviewSource(dummyMovieId)
                }
            }

            Then("Review source should emit correct data") {
                runBlockingTest {
                    mockUseCase.ratingMovie(dummyMovieId, 0)
                }
            }
        }

        Scenario("Add favorite movie") {
            Given("Mock add favorite movie") {
                coEvery { mockUseCase.addFavorite(dummyMovieItem) } returns Unit
            }

            When("Adding favorite movie") {
                viewModel.addFavoriteMovie(dummyMovieItem)
            }

            Then("Add favorite movie should be called") {
                coEvery { mockUseCase.addFavorite(dummyMovieItem) }
            }
        }

        Scenario("Delete favorite movie") {
            Given("Mock delete favorite movie") {
                coEvery { mockUseCase.deleteFavorite(dummyMovieItem) } returns Unit
            }

            When("Deleting favorite movie") {
                viewModel.deleteFavoriteMovie(dummyMovieItem)
            }

            Then("Delete favorite movie should be called") {
                coEvery { mockUseCase.deleteFavorite(dummyMovieItem) }
            }
        }
    }
})
