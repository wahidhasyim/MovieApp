package com.wahidhasyim.movieapp

import com.wahidhasyim.movieapp.data.network.exception.UnknownException
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import com.wahidhasyim.movieapp.presentation.favorite.FavoriteViewModelImpl
import com.wahidhasyim.movieapp.presentation.home.HomeViewModelImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@ExperimentalCoroutinesApi
class HomeViewModelImplTest: Spek({

    val mockUseCase by memoized { mockk<MovieUseCase>() }
    val viewModel by memoized { HomeViewModelImpl(mockUseCase) }

    Feature("HomeViewModelImplTest") {

        Scenario("Retrieve movie source for a category") {
            When("Retrieving movie source for a category") {
                runBlockingTest {
                    viewModel.movieSource(0)
                }
            }

            Then("Movie source should emit correct data") {
                runBlockingTest {
                    mockUseCase.popularMovie(0)
                }
            }
        }

        Scenario("Retrieve movie source for an unknown category") {

            lateinit var exception: Exception

            Given("Mock unknown category") {
                coEvery { mockUseCase.popularMovie(any()) } throws UnknownException()
            }

            When("Retrieving movie source for an unknown category") {
                runBlockingTest {
                    try {
                        viewModel.movieSource(3).single()
                    } catch (e: Exception) {
                        exception = e
                    }
                }
            }

            Then("Exception should be thrown") {
                assert(exception is UnknownException)
            }
        }
    }
})
