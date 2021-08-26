package com.application.projectarchitecture.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.projectarchitecture.api.APICallHandler
import com.application.projectarchitecture.api.APICallManager
import com.application.projectarchitecture.api.APIType
import com.application.projectarchitecture.model.base.Errors
import com.application.projectarchitecture.model.output.UserProfileResponse


class AuthViewModel : ViewModel(), APICallHandler<Any> {


    var loginSuccess =
        MutableLiveData<UserProfileResponse>()

    var error =
        MutableLiveData<Errors>()

    fun loginAPI(
        email: String?,
        password: String?
    ) {
        val apiCallManager = APICallManager(APIType.SIGN_IN, this)
        apiCallManager.loginAPI(email, password)
    }


    override fun onSuccess(apiType: APIType, response: Any?) {

        when (apiType) {

            APIType.SIGN_IN -> {
                val userProfileResponse = response as UserProfileResponse
                loginSuccess.setValue(userProfileResponse)
            }

            else -> {
            }
        }

    }

    override fun onFailure(apiType: APIType, error: Errors) {
        this.error.value = error
    }
}