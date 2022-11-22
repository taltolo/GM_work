package com.example.gm.view.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.model
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gm.model.Contact
import com.example.gm.viewmodel.ContatViewModel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactProfile(contact : Contact,contactViewModel: ContatViewModel = viewModel()){

    var firstName by rememberSaveable { mutableStateOf(contact.firstName) }
    var lastName by rememberSaveable { mutableStateOf(contact.lastName) }
    val phoneChage  = mutableStateOf("")
    var emailChange = mutableStateOf("")

    LazyColumn() {
        stickyHeader {
            Column(
                modifier = Modifier

                    .padding(8.dp)
            ) {
//            ProfileHeader()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                )
                {

                    Text(
                        text = "Save",
                        modifier = Modifier.clickable {

                            contactViewModel.updateContact(contact, "0545999999")
                        },
                        fontSize = 18.sp
                    )


                }


                ProfileImage(contact.photoUrl, contact.firstName, contact.lastName)
            }
        }
        item(contact.firstName) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "First Name", modifier = Modifier.width(100.dp))
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black
                    )
                )
            }
        }
        item(contact.lastName) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Last Name", modifier = Modifier.width(100.dp))
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black
                    )
                )
            }
        }
        for (pn in contact.phoneNumber.keys) {
            item(pn) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var phone by rememberSaveable { mutableStateOf(pn) }

                    Text(text = contact.phoneNumber[pn]+" Number", modifier = Modifier.width(100.dp))
                    phone?.let {
                        TextField(
                            value = it,
                            onValueChange = { phone = it },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                textColor = Color.Black
                            )
                        )
                    }
                }
            }
        }
        for (et in contact.email.keys) {
            item(et) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var email by rememberSaveable { mutableStateOf(et) }
                    Text(text = contact.email[et] +" Email", modifier = Modifier.width(100.dp))
                    email?.let {
                        TextField(
                            value = it,
                            onValueChange = { email = it },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                textColor = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
 }



