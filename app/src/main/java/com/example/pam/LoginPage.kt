import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pam.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(R.drawable.background2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart,
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color(0xFFF2A297),
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it }, Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(70.dp)
                            .align(Alignment.CenterHorizontally),
                        label = { Text("Email") },
                        shape = RoundedCornerShape(20.dp),
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(70.dp)
                            .align(Alignment.CenterHorizontally),
                        label = { Text("Password") },
                        shape = RoundedCornerShape(20.dp),

                        )
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedButton(
                        onClick = {
                            performLogin(
                                context,
                                auth,
                                email,
                                password,
                                navController
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = White,
                            containerColor = Color(0xFFADD6B8)
                        ),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            "Sign In",
                            style = TextStyle(
                                color = Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }
                }
            }

        }
    }
}

private fun performLogin(
    context: android.content.Context,
    auth: FirebaseAuth,
    email: String,
    password: String,
    navController: NavController
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate("MainPage")
                Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
}