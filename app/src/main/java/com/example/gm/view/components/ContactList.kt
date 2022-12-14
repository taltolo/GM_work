package com.example.gm.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gm.model.Contact
import com.example.gm.viewmodel.ContatViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactList(
    navController: NavController,
    viewModel: ContatViewModel,
    contactL: List<Contact>
) {

    val stringFilter = viewModel.stringFilter.value

    Scaffold() {
        LazyColumn()
         {
            stickyHeader {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                        {
                    Text(   modifier = Modifier

                        .background(MaterialTheme.colors.background) ,
                        text = "Contacts" ,
                        style = MaterialTheme.typography.h4,
                        textAlign= TextAlign.Center
                    )
                    TextField(
                        value = stringFilter,
                        onValueChange = { newValue -> viewModel.onFilterList(newValue ,
                            contactL)  },
                        label = {Text("Search by name")},
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Black

                        )

                    )
                }
            }
            items(contactL) { contact ->
                ContactRow(contact = contact ,
                    navController = navController)
            }
        }
    }

}






