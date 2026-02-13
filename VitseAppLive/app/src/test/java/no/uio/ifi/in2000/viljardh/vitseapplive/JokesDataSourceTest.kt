package no.uio.ifi.in2000.viljardh.vitseapplive

import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.viljardh.vitseapplive.data.jokes.JokesDataSource
import org.junit.Test

class JokesDataSourceTest {

    @Test
    fun testFetchJokes() = runBlocking {
        val dataSource = JokesDataSource()
        val jokes = dataSource.fetchJokes()

        jokes.forEach {
            println(it.joke)
        }

        assert(jokes.isNotEmpty()) { "ruh roh" }
    }
}

