package com.application.projectarchitecture.api

import android.content.Intent
import com.application.projectarchitecture.R
import com.application.projectarchitecture.base.App
import com.application.projectarchitecture.model.base.BaseResponse
import com.application.projectarchitecture.model.base.Errors
import com.application.projectarchitecture.model.output.UserProfileResponse
import com.application.projectarchitecture.ui.splash.SplashActivity
import com.application.projectarchitecture.utils.PreferenceKeeper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class APICallManager<T>(
    private val mCallType: APIType,
    private val mAPICallHandler: APICallHandler<T>
) : Callback<BaseResponse<T>> {


    override fun onResponse(call: Call<BaseResponse<T>>?, response: Response<BaseResponse<T>>) {
        if (response.code() == APIStatusCode.OK || response.code() == APIStatusCode.CREATED || response.code() == APIStatusCode.NO_CONTENT) {
            val body = response.body()
            if (body != null) {
                if (body.statusCode == 1) {
                    mAPICallHandler.onSuccess(mCallType, body.responseData)
                } else {
                    if (body.error != null) {

                        if (body.error?.errorCode == 17) {
                            PreferenceKeeper.getInstance().isLogin = false
                            PreferenceKeeper.getInstance().accessToken = null
                            val intent =
                                Intent(App.INSTANCE, SplashActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            App.INSTANCE.startActivity(intent)

                        } else {
                            mAPICallHandler.onFailure(mCallType, body.error!!)
                        }
                    } else {
                        val errors = Errors()
                        val errorMessage =
                            App.INSTANCE.resources.getString(R.string.error_something_wrong)
                        errors.errorMessage = errorMessage
                        mAPICallHandler.onFailure(mCallType, errors)
                    }
                }
            }
        } else {
            val errors = Errors()
            val errorMessage =
                App.INSTANCE.resources.getString(R.string.error_something_wrong)
            errors.errorMessage = errorMessage
            mAPICallHandler.onFailure(mCallType, errors)
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>>?, throwable: Throwable) {
        val errorCode = 0
        val message: String? =
            if (throwable is UnknownHostException || throwable is SocketException || throwable is SocketTimeoutException) {
                App.INSTANCE.resources.getString(R.string.error_something_wrong)
            } else {
                throwable.message
            }
        val errors = Errors()
        errors.errorMessage = message
        mAPICallHandler.onFailure(mCallType, errors)
    }

    fun loginAPI(
        email: String?,
        password: String?
    ) {
        APIClient.getClient()
            .loginAPI(email, password)
            .enqueue(this@APICallManager as Callback<BaseResponse<UserProfileResponse?>>)
    }
}
