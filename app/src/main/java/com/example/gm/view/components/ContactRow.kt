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



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactRow(contact: Contact, navController: NavController){

         Card(
                onClick = {

                    val bundle = Bundle()
                    bundle.putSerializable("contact",contact)

                    navController.navigate(R.id.viewContact,bundle)
                },
                modifier = Modifier.padding(8.dp),
                elevation = 6.dp
            )
            {
                Row(

                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.width(5.dp))

                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = contact.firstName + " " + contact.lastName,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black,
                    )

                }
            }

}