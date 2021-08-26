package com.application.projectarchitecture.api

import com.application.projectarchitecture.model.base.Errors

interface APICallHandler<T> {

    fun onSuccess(apiType: APIType, response: T?)

    fun onFailure(apiType: APIType, error: Errors)
}