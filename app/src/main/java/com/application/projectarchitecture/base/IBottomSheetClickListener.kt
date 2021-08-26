package com.application.projectarchitecture.base

import com.application.projectarchitecture.model.Media


interface IBottomSheetClickListener {
    fun onBottomSheetItemClicked(
        position: Int,
        type: BottomSheetType?,
        data: Media?
    )
}