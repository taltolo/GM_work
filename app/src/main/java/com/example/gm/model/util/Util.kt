package com.example.gm.model.util

import android.provider.ContactsContract

class Util {


        companion object {
            fun typePhone(type: Int): String {
                when (type) {
                    ContactsContract.CommonDataKinds.Phone.TYPE_HOME -> return "Home"
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE -> return "Mobile"
                    ContactsContract.CommonDataKinds.Phone.TYPE_WORK -> return "Work"
                    ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE -> return "Work Mobile"
                    ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME -> return "Fax Home"
                    ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM -> return "Custom"
                    ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK -> return "Fax Work"
                    ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT -> return "Assistant"
                    ContactsContract.CommonDataKinds.Phone.TYPE_CAR -> return "Car"
                    ContactsContract.CommonDataKinds.Phone.TYPE_OTHER -> return "Other"
                    ContactsContract.CommonDataKinds.Phone.TYPE_PAGER -> return "Pager"
                    ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK -> return "Callback"
                    ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN -> return "Company Main"
                    ContactsContract.CommonDataKinds.Phone.TYPE_MAIN -> return "Main"
                    ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX -> return "Other Fax"

                }
                return ""
            }

            fun typeEmail(type: Int): String {
                when (type) {
                    ContactsContract.CommonDataKinds.Email.TYPE_HOME -> return "Home"
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> return "Mobile"
                    ContactsContract.CommonDataKinds.Email.TYPE_WORK -> return "Work"
                    ContactsContract.CommonDataKinds.Email.TYPE_OTHER -> return "Other"
                    ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM -> return "Custom"

                }
                return ""
            }
        }

}