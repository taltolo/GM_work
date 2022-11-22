package com.example.gm.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gm.model.Contact
import com.example.gm.model.ContactRepository
import com.example.gm.model.ContactRepository.Companion.retrieveContactsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContatViewModel (application: Application) : AndroidViewModel(application) {

    var firstVisibleItemOffset: Int =0
    var firstVisibleItemIdx: Int  = 0
    private val context = getApplication<Application>(). applicationContext
    private val contentResolver : ContentResolver = context.contentResolver

    private var _contact: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactl: MutableLiveData<List<Contact>> = _contact
    var contact: MutableList<Contact> = mutableListOf()
    var stringFilter =mutableStateOf("")
    var firstNameC =mutableStateOf("")
    var lastNameC =mutableStateOf("")
    var phoneMapC : MutableMap<String,String> = mutableMapOf()
    var emailMapC  : MutableMap<String,String> = mutableMapOf()

    private var _contactFilter: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactFilter: MutableLiveData<List<Contact>> = _contactFilter


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


    fun updateContact(contact : Contact,newNumber: String) {

//        contact.id?.let { ContactRepository.updatePhone(it.toLong(), contact.phoneNumber[0], newNumber,contentResolver ) }

    }

    fun updateContactInfo(contact : Contact,newNumber: String,newEmail: String,newFirstName : String, newLastName: String) {


    }


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

    fun getContactInfo(contact : Contact){
        firstNameC.value = contact.firstName
        lastNameC.value = contact.lastName
        phoneMapC = contact.phoneNumber
        emailMapC = contact.email
    }

    fun onChangePhoneNum(newPhone: String, pn: String) {

    }

}


