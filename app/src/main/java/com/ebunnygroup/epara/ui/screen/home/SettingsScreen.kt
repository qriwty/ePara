import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ebunnygroup.epara.data.auth.login.LoginViewModel
import com.ebunnygroup.epara.data.home.HomeViewModel

@Composable
fun SettingsScreen(
    onLogoutClick: () -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Налаштування???",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Це ідеальний додаток, тут немає чого налаштовувати",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                )
                Text(
                    text = "Але тримати ми Вас не будемо ...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                        //.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                )
            }
        }
        Button(
            onClick = {
                homeViewModel.onLogout(onLogoutClicked = onLogoutClick)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Вийти")
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun SettingsScreenPreview() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationScreenTest(navController = navController)
//        }
//    ) {}
//
//    SettingsScreen()
//}
