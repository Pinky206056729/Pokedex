package com.siphokazi.pokedex.domain.common

/**
 * A custom sealed class representing the outcome of an operation.
 * It strictly separates successful data from failure information.
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Failure(val exception: Throwable? = null) : Result<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Failure -> null
    }

    fun exceptionOrNull(): Throwable? = when (this) {
        is Success -> null
        is Failure -> exception
    }
}