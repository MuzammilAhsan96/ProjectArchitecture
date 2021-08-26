package com.application.projectarchitecture.api
import com.application.projectarchitecture.model.base.BaseResponse
import com.application.projectarchitecture.model.output.UserProfileResponse
import retrofit2.Call
import retrofit2.http.*


interface IApiService {

    @FormUrlEncoded
    @POST("v1/employee/login")
    fun loginAPI(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<BaseResponse<UserProfileResponse?>>

}