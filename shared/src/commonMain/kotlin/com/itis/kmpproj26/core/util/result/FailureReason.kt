package com.itis.kmpproj26.core.util.result

sealed class FailureReason {
    data class Network(val message: String? = null) : FailureReason()
    data class BadRequest(val message: String? = null) : FailureReason()
    data class Server(val message: String? = null) : FailureReason()
    data class Conflict(val message: String? = null) : FailureReason()
    data class Unknown(val message: String? = null) : FailureReason()
}
