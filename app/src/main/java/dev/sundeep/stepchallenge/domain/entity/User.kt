package dev.sundeep.stepchallenge.domain.entity

data class User (
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val isAdmin: Boolean
)
