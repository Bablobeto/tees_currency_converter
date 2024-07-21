package com.example.teescurrencyconverter.ui.app.pages

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

@Composable
fun ProfileScreen(
    navController : NavController,
    vm : FbViewModel
){
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val context = LocalContext.current

    // Form variables
    var  name by remember { mutableStateOf(
        if(null != auth.currentUser)
            if(null != vm.customData.name) vm.customData.name.uppercase(
                Locale.ROOT
            )
            else auth.currentUser!!.displayName.toString()
                .uppercase(
                    Locale.ROOT
                )
        else "Buddy")
    }
    val  email by remember { mutableStateOf(user?.email ?: "") }
    var  password by remember { mutableStateOf("") }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showDialog : Boolean = false

    if (null == auth.currentUser || vm.isLogout.value) {
        navController.popBackStack()
        navController.navigate(Screen.Home.AUTHENTICATION_ROUTE)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ){uri: Uri? ->
        uri?.let{
          bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
            else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
            Log.d("TAG Bitmap", bitmap.toString())
        }
    }

    val cLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ){
        bitmap = it
    }

    TeesCurrencyConverterTheme {

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column (
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(Color.White),
            ){

                // Top Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ){

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Column(
                            modifier = Modifier.weight(2f)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = stringResource(id = R.string.google_logo),
                                modifier = Modifier.size(90.dp)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        ){

                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Go back to previous page",
                                tint = Color.Black
                            )

                            Text(
                                text = "Go back",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Forms
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = "Profile",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = TextUnit(30f, TextUnitType.Sp),
                            modifier = Modifier.padding(15.dp)
                        )

                        Text(
                            text = "Provide the details below to update your profile information",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Registration.route)
                            },
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        OutlinedTextField(
                            value = name,
                            label = { Text("Name") },
                            placeholder = { Text("Philip") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "First name icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { newText -> name = newText }
                        )

                        OutlinedTextField(
                            value = email,
                            label = { Text("Email", color = Color.Black) },
                            placeholder = { Text("@live.tees.ac.uk") },
                            readOnly = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Email,
                                    contentDescription = "Email icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {  }
                        )

                        OutlinedTextField(
                            value = password,
                            label = { Text("Password", color = Color.Black) },
                            placeholder = { Text("@live.tees.ac.uk") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = "Password icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { newText -> password = newText }
                        )


                      Spacer(modifier = Modifier.height(40.dp))

                        // Add profile image
                        if(null != auth.currentUser?.photoUrl && bitmap == null){
                            Image(
                                painter = rememberImagePainter(auth.currentUser?.photoUrl.toString()),
                                contentDescription = "Profile image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Purple80)
                                    .size(150.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Purple80,
                                        shape = CircleShape
                                    )
                            )
                        }
                        else if (bitmap != null){
                            Image(
                                bitmap = bitmap?.asImageBitmap()!!,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Purple80)
                                    .size(150.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Purple80,
                                        shape = CircleShape
                                    )
                                    .clickable { showDialog = true }
                            )
                        }
                        else {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Purple80)
                                    .size(150.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Purple80,
                                        shape = CircleShape
                                    )
                                    .clickable { showDialog = true }
                            )
                        }
                        // End add profile image

                        Spacer(modifier = Modifier.height(20.dp))

//                        if(showDialog) {
                        // Image upload options
                        Row {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_camera_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(50.dp)
                                        .clickable {
                                            cLauncher.launch()
                                            showDialog = false
                                        }
                                )
                                Text(
                                    text = "Camera",
                                    color = Color.White
                                )
                            }
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_browse_gallery_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(50.dp)
                                        .clickable {
                                            launcher.launch("image/*")
                                            showDialog = true
                                        }
                                )
                            }
                        }



                        ElevatedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 20.dp, 0.dp, 20.dp),
                            shape = RoundedCornerShape(12.dp), // Use a rounded corner shape for a softer look
                            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                            onClick = {
                                vm.saveExtraProfileData(name, password)
                                bitmap?.let {
                                    vm.uploadBitmapToFirebase(name, it)
                                }
                            }
                        ) {Text(
                            text = "Update changes",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                            modifier = Modifier.padding(15.dp)
                        )}

                    }
                    // End of Forms

                }

            }
        }
    }
}
