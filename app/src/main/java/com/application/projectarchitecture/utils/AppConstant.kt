package com.application.projectarchitecture.utils

import com.application.projectarchitecture.model.output.UserProfile

object AppConstant {

    const val DEVICE_TYPE = 1
    const val SPLASH_DELAY: Long = 2000
    const val DETECT_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    var USER: UserProfile? = UserProfile()

    interface BK {
        companion object {
            const val TYPE = "type"
        }
    }

    interface PKN {
        companion object {
            const val ACCESS_TOKEN = "access_token"
        }
    }

    interface MT {
        companion object {
            const val UPLOAD_MEDIA = "images"
            const val TEXT_PLAIN = "text/plain"
        }
    }
}