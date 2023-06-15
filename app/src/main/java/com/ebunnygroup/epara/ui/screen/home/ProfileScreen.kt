package com.ebunnygroup.epara.ui.screen.home

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ebunnygroup.epara.R
import com.ebunnygroup.epara.ui.common.ScreenContent

@Composable
fun ProfileScreen(
    screenName: String,
    previousScreen: String?,
    onNextScreenClick: () -> Unit,
    profilePhoto: Int,
    studentName: String,
    studentGroup: String,
    studentInstitute: String
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(128.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.medium
                ),
            //elevation = 4.dp
        ) {
            Image(
                painter = painterResource(profilePhoto),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.large
                ),
            //elevation = 4.dp
        ) {
            Text(
                text = studentName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.large
                ),
            //elevation = 4.dp
        ) {
            Text(
                text = studentGroup,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.large
                ),
            //elevation = 4.dp
        ) {
            Text(
                text = studentInstitute,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

    //ScreenContent(screenName, previousScreen, onNextScreenClick)
    //Column(
        //modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    //) {
        //Image(
            //painter = painterResource(profilePhoto),
            //contentDescription = null,
            //modifier = Modifier.size(128.dp)
        //)
        //Spacer(modifier = Modifier.height(16.dp))
        //Text(text = studentName, style = MaterialTheme.typography.titleLarge)
        //Text(text = studentGroup, style = MaterialTheme.typography.titleLarge)
        //Text(text = studentInstitute, style = MaterialTheme.typography.titleLarge)
    //}
//}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreenPreview() {

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationScreenTest(navController = navController)
        }
    ) {}

    ProfileScreen(
        screenName = "Profile",
        previousScreen = "Home",
        onNextScreenClick = {},
        profilePhoto = R.drawable.ic_launcher_foreground,
        studentName = "Surname Name Patronymic",
        studentGroup = "Group: NUP-202",
        studentInstitute = "Institute: AIR"
    )
}
