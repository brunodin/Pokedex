package com.bruno.pokedex.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsKtTest {

    @Test
    fun `when firstCharUpperCase is called then string returns with first character in uppercase`() {
        val result = POKEMON.firstCharUpperCase()
        assertEquals(POKEMON_WITH_FIRST_LETTER_UPPERCASE, result)
    }

    private companion object {
        const val POKEMON = "charizard"
        const val POKEMON_WITH_FIRST_LETTER_UPPERCASE = "Charizard"
    }
}