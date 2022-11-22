package com.example.gm.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
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
            val builder = StringBuilder()

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


                    val cursorEmail = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " =?", arrayOf(id),null)
                    if(cursorEmail != null && cursorEmail.count > 0){
                        while (cursorEmail.moveToNext()){
                            val email = cursorEmail.getString(cursorEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                            val type=cursorEmail.getInt(cursorEmail.getColumnIndex(Email.TYPE))
                            Log.i("Email TYPE", type.toString())
                            emailList.put(email,typeEmail(type))
                        }
                    }
                    cursorEmail?.close()
                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                        if(cursorPhone != null && cursorPhone.count > 0) {
                            while (cursorPhone.moveToNext()) {
                               val phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(Phone.NUMBER))
                                val type=cursorPhone.getInt(cursorPhone.getColumnIndex(Phone.TYPE))
                                builder.append("Contact: ").append(name).append(", Phone Number: ").append(
                                    phoneNumValue).append("\n\n")
                                Log.e("Name ===>",phoneNumValue + name)
                                phoneNumValueList.put(phoneNumValue,typePhone(type))
                            }
                        }
                        cursorPhone?.close()
                        contacts.add(Contact(id,given,family,phoneNumValueList,emailList,photoUrl))
                    }


                }
            } else {
                //   toast("No contacts available!")
            }
            cursor?.close()
            return contacts
        }

        private fun typePhone(type: Int): String {
            when (type) {
                Phone.TYPE_HOME -> return "Home"
                Phone.TYPE_MOBILE -> return "Mobile"
                Phone.TYPE_WORK -> return "Work"
                Phone.TYPE_WORK_MOBILE -> return "Work Mobile"
                Phone.TYPE_FAX_HOME -> return "Fax Home"
                Phone.TYPE_CUSTOM -> return "Custom"
                Phone.TYPE_FAX_WORK -> return "Fax Work"
                Phone.TYPE_ASSISTANT -> return "Assistant"
                Phone.TYPE_CAR -> return "Car"
                Phone.TYPE_OTHER -> return "Other"
                Phone.TYPE_PAGER -> return "Pager"
                Phone.TYPE_CALLBACK -> return "Callback"
                Phone.TYPE_COMPANY_MAIN -> return "Company Main"
                Phone.TYPE_MAIN -> return "Main"
                Phone.TYPE_OTHER_FAX -> return "Other Fax"

            }
            return ""
        }

        private fun typeEmail(type: Int): String {
            when (type) {
                Email.TYPE_HOME -> return "Home"
                Email.TYPE_MOBILE -> return "Mobile"
                Email.TYPE_WORK -> return "Work"
                Email.TYPE_OTHER -> return "Other"
                Email.TYPE_CUSTOM -> return "Custom"

            }
            return ""
        }

        fun updatePhone(
            contactId:Long, existingNumber: String, newNumber:String,
            contentResolver: ContentResolver) {
            val contentValues = ContentValues()
            contentValues.put(Phone.NUMBER, newNumber)

            val where =  ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.RawContacts.Data.MIMETYPE + "=?" + " AND " + Phone.NUMBER + "=?"
            val whereArgs = arrayOf<String>((contactId).toString(), Phone.CONTENT_ITEM_TYPE, existingNumber)

            contentResolver.update(ContactsContract.Data.CONTENT_URI, contentValues, where, whereArgs)
        }


    }
}

