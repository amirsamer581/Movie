package com.example.signin.domain.model

sealed class ResultState<out R> {
    data object Loading : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val message: String, val errorCode: Int? = null) : ResultState<Nothing>()
    data object Idle : ResultState<Nothing>()
    //todo use Result state to my code
}