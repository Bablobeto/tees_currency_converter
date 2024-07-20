package com.example.teescurrencyconverter.ui.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme

@Composable
fun Logo(size : Int = 150){
    TeesCurrencyConverterTheme {
        val logo = (if (isSystemInDarkTheme())
            painterResource(id = R.drawable.logo)
        else painterResource(id = R.drawable.logo)).also {

                Image(
                    painter = it,
                    contentDescription = stringResource(id = R.string.google_logo),
                    modifier = if(size in (1..200)) {
                        Modifier.size(size.dp)
                    }
                    else{
                        Modifier.size(120.dp)
                    }
                )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopSectionPreview(){
    Logo()
}