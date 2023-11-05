package com.levid.random_users.ui.user

import com.levid.random_users.data.remote.ApiResponse
import com.levid.random_users.data.remote.dto.UserDto

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<UserDto> = emptyList(),
    val error: String? = null
)
