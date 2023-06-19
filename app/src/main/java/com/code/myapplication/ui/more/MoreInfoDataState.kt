package com.code.myapplication.ui.more

import com.code.myapplication.data.local.UserEntity

data class MoreInfoDataState(
    val isLoading: Boolean = false, val moreInfo: UserEntity? = null
)
