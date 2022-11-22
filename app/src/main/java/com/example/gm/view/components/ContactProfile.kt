package com.example.gm.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gm.model.Contact

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactProfile(contact : Contact){

    var firstName by rememberSaveable { mutableStateOf(contact.firstName) }
    var lastName by rememberSaveable { mutableStateOf(contact.lastName) }

    LazyColumn() {
        stickyHeader {
            Column(
                modifier = Modifier

                    .padding(8.dp)
            ) {

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
                    enabled = false,
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
                    enabled = false,
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
                            enabled = false,
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
                            enabled = false,
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



