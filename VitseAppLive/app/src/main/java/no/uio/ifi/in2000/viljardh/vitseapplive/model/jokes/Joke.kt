package no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes

data class Joke(
    val category: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val joke: String,
    val lang: String,
    val safe: Boolean,
    val type: String
)