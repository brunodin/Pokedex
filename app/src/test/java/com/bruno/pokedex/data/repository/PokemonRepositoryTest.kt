package com.bruno.pokedex.data.repository

import com.bruno.pokedex.data.api.PokemonApi
import com.bruno.pokedex.data.response.PokemonDetailResponse
import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import com.bruno.pokedex.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.net.SocketTimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val api: PokemonApi = mockk(relaxed = true)
    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = PokemonRepositoryImpl(api, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `when getPokemonPaginated is called then request is successful`() = runTest {
        //Prepare
        coEvery { api.getPokemonPaginated(PAGE) } returns PokemonPaginatedResponse.mock()
        //Action
        val result = repository.getPokemonPaginated(PAGE)
        //Check
        coVerify(exactly = 1) { api.getPokemonPaginated(PAGE) }
        assertEquals(PokemonPaginated.mock(), result.getOrThrow())
    }

    @Test
    fun `when getPokemonPaginated is called then request fails`() = runTest {
        //Prepare
        val errorMock = SocketTimeoutException()
        coEvery { api.getPokemonPaginated(PAGE) } throws errorMock
        //Action
        val result = repository.getPokemonPaginated(PAGE)
        //Check
        assertTrue(result.isFailure)
    }

    @Test
    fun `when getPokemonDetail is called then request is successful`() = runTest {
        //Prepare
        coEvery { api.getPokemonDetail(ID) } returns PokemonDetailResponse.mock()
        //Action
        val result = repository.getPokemonDetail(ID)
        //Check
        coVerify(exactly = 1) { api.getPokemonDetail(ID) }
        assertEquals(PokemonDetail.mock(), result.getOrThrow())
    }

    @Test
    fun `when getPokemonDetail is called then request fails`() = runTest {
        //Prepare
        val errorMock = SocketTimeoutException()
        coEvery { api.getPokemonDetail(ID) } throws errorMock
        //Action
        val result = repository.getPokemonDetail(ID)
        //Check
        assertTrue(result.isFailure)
    }

    private companion object {
        const val PAGE = 1
        const val ID = 1
    }
}