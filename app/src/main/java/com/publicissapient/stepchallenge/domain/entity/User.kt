package com.publicissapient.stepchallenge.domain.entity

data class User (
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val isAdmin: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Goal) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        var result = 2
        result = 31 * result + id.hashCode()
        return result
    }
}
