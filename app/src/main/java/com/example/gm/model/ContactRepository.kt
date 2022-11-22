package com.example.gm.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import com.example.gm.model.util.Util
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.core.database.getStringOrNull
import kotlinx.coroutines.*


class ContactRepository ( ) {

    companion object {
        suspend fun retrieveContactsList(contentResolver : ContentResolver): MutableList<Contact> {
                return getListContacts(contentResolver)

          }

   @SuppressLint("Range")
   suspend fun getListContacts(contentResolver : ContentResolver): MutableList<Contact> {
            val contacts: MutableList<Contact> = mutableListOf()
            var given = ""
            var family = ""
            val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val emailList : MutableMap<String,String> = mutableMapOf()
                    val phoneNumValueList : MutableMap<String,String> = mutableMapOf()
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                    val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()
                    var photoUrl = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
                    if (photoUrl==null){
                        photoUrl= ""
                    }

                    val whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = " + id
                    val whereNameParams =
                        arrayOf(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    val nameCur = contentResolver.query(
                        ContactsContract.Data.CONTENT_URI,
                        null,
                        whereName,
                        whereNameParams,
                        ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME
                    )
                    if (nameCur != null && nameCur.count > 0) {
                        while (nameCur.moveToNext()) {

                            nameCur.getStringOrNull(
                                nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)
                            )?.let {
                                given = it
                            }

                            nameCur.getStringOrNull(
                                nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME)
                            )?.let {
                                family = it
                            }

                        }
                    }
                    nameCur?.close()


                    val cursorEmail = contentResolver.query(Email.CONTENT_URI,
                        null,Email.CONTACT_ID + " =?", arrayOf(id),null)
                    if(cursorEmail != null && cursorEmail.count > 0){
                        while (cursorEmail.moveToNext()){
                            val email = cursorEmail.getString(cursorEmail.getColumnIndex(Email.DATA))
                            val type=cursorEmail.getInt(cursorEmail.getColumnIndex(Email.TYPE))
                            emailList.put(email,Util.typeEmail(type))
                        }
                    }
                    cursorEmail?.close()
                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                            Phone.CONTENT_URI,
                            null, Phone.CONTACT_ID + "=?", arrayOf(id), null)

                        if(cursorPhone != null && cursorPhone.count > 0) {
                            while (cursorPhone.moveToNext()) {
                               val phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(Phone.NUMBER))
                                val type=cursorPhone.getInt(cursorPhone.getColumnIndex(Phone.TYPE))
                                phoneNumValueList.put(phoneNumValue,Util.typePhone(type))
                            }
                        }
                        cursorPhone?.close()
                        contacts.add(Contact(id,given,family,phoneNumValueList,emailList,photoUrl))
                    }


                }
            }
            cursor?.close()
            return contacts
        }


    }
}

