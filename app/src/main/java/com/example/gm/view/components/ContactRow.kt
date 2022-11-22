package com.example.gm.view.components

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.example.gm.model.Contact
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import com.example.gm.R
import androidx.compose.material.Card
import androidx.navigation.NavController
import com.example.gm.viewmodel.ContatViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactRow(contact: Contact, navController: NavController){
    // on below line we are setting data for each item of our listview.
            // on below line we are creating a card for our list view item.
         Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                    val bundle = Bundle()
                    bundle.putSerializable("contact",contact)

                    navController.navigate(R.id.viewContact,bundle)
                },
                modifier = Modifier.padding(8.dp),
                elevation = 6.dp
            )
            {
                Row(
                    // for our row we are adding modifier
                    // to set padding from all sides.
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    // on below line inside row we are adding spacer
                    Spacer(modifier = Modifier.width(5.dp))

                    // on below line we are adding image to display the image.
 //                   Image(
//                        // on below line we are specifying the drawable image for our image.
//                        painter = painterResource(id = contact.photoUrl),
//                        // on below line we are setting height
//                        // and width for our image.
//                        modifier = Modifier
//                            .height(60.dp)
//                            .width(60.dp)
//                            .padding(5.dp)
//                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = contact.firstName + " " + contact.lastName,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black,
                    )

                }
            }

}