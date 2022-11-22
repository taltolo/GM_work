package com.example.gm.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.gm.view.components.util.util



@Composable
fun ProfileImage(photoUrl : String,firstName : String, lastName: String){

    val imageUri = rememberSaveable { mutableStateOf(photoUrl) }
    val painter = rememberImagePainter(imageUri.value)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            if (imageUri.value.length>0){
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable { launcher.launch("image/*") },
                    contentScale = ContentScale.Crop
                )
            }
            else{
                if (lastName.isEmpty())
                    CircleImage(firstName,"  ")
                else
                    CircleImage(firstName,lastName)

            }
        }
    }
}



@Composable
fun CircleImage(firstName : String, lastName: String){

    androidx.compose.material.Text(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
            .drawBehind {
                drawCircle(
                    color = util.getRandonColor(),
                    radius = this.size.maxDimension
                )
            },

        text = firstName.substring(0,1)+lastName.substring(0,1),fontSize = 30.sp

    )

}

