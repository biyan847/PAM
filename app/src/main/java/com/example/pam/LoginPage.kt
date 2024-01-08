import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pam.R
import com.example.pam.ui.add.DestinasiEntry
import com.example.pam.ui.theme.PAMTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavController
){
    val context = LocalContext.current
    lateinit var auth: FirebaseAuth
    auth = Firebase.auth
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.30f),
            Alignment.TopEnd,){
            Image(painter = painterResource(id = R.drawable.purpleback), contentDescription = "Back App", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo App", modifier = Modifier
                    .weight(5f)
                    .size(250.dp)
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color(0xFFC2AECF),
            ),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(value = emailText,
                    onValueChange = { emailText = it },
                    label = { Text(text = "Email")},
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email Icon")
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                OutlinedTextField(value = passwordText,
                    onValueChange = {passwordText = it},
                    label = { Text(text = "Password")},
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Pass Icon")
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding( vertical = 5.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { (
                            auth.signInWithEmailAndPassword(emailText, passwordText)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        navController.navigate(DestinasiEntry.route)
                                        Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(

                        Color.Magenta
                    )
                )
                {
                    Text(text = "Sign in")
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }

}