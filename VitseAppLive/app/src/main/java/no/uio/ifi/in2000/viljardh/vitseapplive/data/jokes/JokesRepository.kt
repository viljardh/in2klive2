package no.uio.ifi.in2000.viljardh.vitseapplive.data.jokes

import no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes.Joke

interface JokesRepository {
    suspend fun getJokesList(): List<Joke>

    class JokesRepositoryImpl(
        private val jokesDataSource: JokesDataSource = JokesDataSource()
    ) : JokesRepository {
        override suspend fun getJokesList(): List<Joke> {
            return jokesDataSource.fetchJokes()
        }
    }
}