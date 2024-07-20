package com.example.teescurrencyconverter.ui.app.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
//import com.teescurrencyconverter.onetap.OneTapSignInWithGoogle
//import com.teescurrencyconverter.onetap.rememberOneTapSignInState

@Composable
fun SignInWithGoogle(
    navController: NavController,
    vm: FbViewModel
){
//    val state = rememberOneTapSignInState()
//    OneTapSignInWithGoogle(
//        state = state,
//        clientId = "843243749931-2nvtrn4hk2uvv40vhr9av797nal3jagh.apps.googleusercontent.com",
//        onTokenIdReceived = { tokenId ->
//            Log.d("Google signing dismissed", "sfddsfsdf")
//
//            navController.popBackStack()
//            navController.navigate(LANDINGS_ROUTE)
//        },
//        onDialogDismissed = { message ->
//            Log.d("Google signing dismissed", message)
//        }
//    )

    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(10.dp)
                .clickable {
//                    state.open()
                }
        ) {

            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = stringResource(id = R.string.google_logo),
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(id = R.string.google_logo)
            )

        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun SignInWithGooglePreview(){
//    SignInWithGoogle()
//}