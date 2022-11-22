package com.example.gm.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gm.model.Contact


@Composable
fun ContactHeader(contactList: MutableList<Contact>) {


            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ){
            Text(   modifier = Modifier

                .background(MaterialTheme.colors.background) ,
                text = "Contacts" ,
                style = MaterialTheme.typography.h4,
               textAlign= TextAlign.Center
            )
            OutlinedButton(onClick = { contactList.sortBy { it.firstName }} ) {
                Text("Sort",style = TextStyle(fontSize = 15.sp), color = Color.Black)
            }
        }

}
