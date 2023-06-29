package com.example.core.network.model.mapper

import com.example.core.network.model.UserErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<UserErrorResponse> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): UserErrorResponse {
        return UserErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}
