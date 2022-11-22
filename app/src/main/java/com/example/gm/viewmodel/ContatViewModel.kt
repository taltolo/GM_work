package com.example.gm.viewmodel

import android.app.Application
import android.content.ContentResolver
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gm.model.Contact
import com.example.gm.model.ContactRepository.Companion.retrieveContactsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContatViewModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>(). applicationContext
    private val contentResolver : ContentResolver = context.contentResolver
    private var _contact: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactl: MutableLiveData<List<Contact>> = _contact
    var contact: MutableList<Contact> = mutableListOf()
    var stringFilter =mutableStateOf("")

    private var _contactFilter: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactFilter: MutableLiveData<List<Contact>> = _contactFilter

// using a separate thread to retrieve all the content data
    @JvmName("getContact1")
    fun getContact(): MutableList<Contact> {

        viewModelScope.launch {
            contact = (getAllContact())
            _contact.value=contact
            _contactFilter.value=contact
        }
        return contact
        }
       suspend fun getAllContact():  MutableList<Contact> {
           return withContext(Dispatchers.IO) {
               (retrieveContactsList(contentResolver))

                        }
           }

//Filtering in real time the List of contacts by user input
    fun onFilterList(stringFilter: String, contactL: List<Contact>) {
        this.stringFilter?.value=stringFilter.toLowerCase()
       if(stringFilter.isEmpty())
           _contact.value=_contactFilter?.value
        else{
        val filterList: MutableSet<Contact> = mutableSetOf()
        for(contact in contactL){
            if( contact.firstName.toLowerCase().contains(stringFilter) ||contact.lastName.toLowerCase().contains(stringFilter) ){
                filterList.add(contact)
            }
        }
        _contact.value =filterList.toList()
        }
    }



}


