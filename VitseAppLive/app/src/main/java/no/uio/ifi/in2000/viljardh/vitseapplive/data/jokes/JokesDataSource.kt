package no.uio.ifi.in2000.viljardh.vitseapplive.data.jokes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes.Joke
import no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes.Jokes

class JokesDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchJokes(): List<Joke> {
        val jokes: Jokes = try {
            println("Trying to fetch")
            val response: HttpResponse = client.get("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist&type=single&amount=10")
            response.body<Jokes>()
        } catch (e: Exception) {
            println("Failed: ${e.message}")
            Jokes(listOf())
        }
        return jokes.jokes
    }
}

//fun main() {
//    val dataSource = JokesDataSource()
//
//    runBlocking {
//        val jokes = dataSource.fetchJokes()
//        jokes.forEach {
//            println(it.joke)
//        }
//    }
//}