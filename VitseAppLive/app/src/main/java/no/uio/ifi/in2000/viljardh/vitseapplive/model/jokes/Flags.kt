package no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes

data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)