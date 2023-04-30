package com.udacity.project4.locationreminders.data.dto



sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val message: String?, val statusCode: Int? = null) : Result<Nothing>()

    fun <R : Any> map(transform: (T) -> R): Result<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> this
        }
    }

    fun <R : Any> flatMap(transform: (T) -> Result<R>): Result<R> {
        return when (this) {
            is Success -> transform(data)
            is Error -> this
        }
    }

    fun getOrNull(): T? {
        return when (this) {
            is Success -> data
            is Error -> null
        }
    }

    fun exceptionOrNull(): Exception? {
        return when (this) {
            is Success -> null
            is Error -> Exception(message)
        }
    }
}
