package com.levid.random_users.ui.user

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.levid.random_users.data.remote.dto.UserDto


@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DisplayList(users = state.users)
    } }

@Composable
fun DisplayList(users: List<UserDto>) {
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: UserDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        //Image row
        RowWithUserPhoto(user)
        //Name row
        RowWithUserInformation(user)
        //Nat row
        RowWithUserNationality(user)
        //Divider
        DivisorLine()
    }
}
@Composable
fun RowWithUserPhoto(user: UserDto){
    //Photo row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.onSurface, CircleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        AsyncImage(
            model = user.picture.large,
            contentDescription = "Profile photo",
            modifier= Modifier
                .border(
                    width = 5.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .aspectRatio(1f, true)
        )
    }
}

@Composable
fun RowWithUserInformation(user: UserDto){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${user.name.title}. ${user.name.first} ${user.name.last}",
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            softWrap = false

        )
    }
}

@Composable
fun RowWithUserNationality(user: UserDto){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = user.nat,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun DivisorLine(){
    Divider(
        color = MaterialTheme.colorScheme.outline,
        thickness = 1.dp,
        modifier = Modifier
            .height(1.dp)
            .padding(start = 10.dp)
    )
}