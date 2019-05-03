package com.example.contactmanager

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract
import android.content.ContentResolver
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri.withAppendedPath
import android.content.ContentUris
import android.graphics.Bitmap
import android.net.Uri
import android.net.Uri.parse
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.widget.AdapterView
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var contactsName: ArrayList<String> = arrayListOf()
    var contactsPhone: ArrayList<String> = arrayListOf()
    var contactsPhoto: ArrayList<String> = arrayListOf()
    var contactsPhotos: ArrayList<Bitmap?> = arrayListOf()
    var contactsEmail: ArrayList<String> = arrayListOf()
    var info: ArrayList<String> = arrayListOf()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactsName = arrayListOf()
        contactsPhone = arrayListOf()
        contactsEmail = arrayListOf()
        info = arrayListOf()
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), 1)
        } else {
            val cr = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

            while (cr.moveToNext()) {
                var hash: HashMap<String, Bitmap> = hashMapOf()
                var name = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                var number = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                var photoUri = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                info.add(name + "." + number)

                if (photoUri != null) {
                    contactsPhoto.add(photoUri)
                    contactsPhotos.add(MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri)))
                    hash[name] = MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri))
                }
                contactsName.add(name)
                contactsPhone.add(number)
            }
            info.sort()
            info.forEach {
                Log.i("Infoooooooooo", it)
            }

            if (contactsName.size > 0) {
                contactsItemList.layoutManager = LinearLayoutManager(this)
                contactsItemList.adapter = ContactsItemListAdapter(this, info)
            }

        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val cr = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

        while (cr.moveToNext()) {
            var hash: HashMap<String, Bitmap> = hashMapOf()
            var name = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            var number = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            var photoUri = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

            info.add(name + "." + number)

            if (photoUri != null) {
                contactsPhoto.add(photoUri)
                contactsPhotos.add(MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri)))
                hash[name] = MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri))
            }
            contactsName.add(name)
            contactsPhone.add(number)
        }
        info.sort()
        info.forEach {
            Log.i("Infoooooooooo", it)
        }

        if (contactsName.size > 0) {
            contactsItemList.layoutManager = LinearLayoutManager(this)
            contactsItemList.adapter = ContactsItemListAdapter(this, info)
        }

    }

    override fun onRestart() {
        super.onRestart()


        contactsName = arrayListOf()
        contactsPhone = arrayListOf()
        contactsEmail = arrayListOf()
        info = arrayListOf()
        val cr = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

        while (cr.moveToNext()) {
            var hash: HashMap<String, Bitmap> = hashMapOf()
            var name = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            var number = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            var photoUri = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

            info.add(name + "." + number)

            if (photoUri != null) {
                contactsPhoto.add(photoUri)
                contactsPhotos.add(MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri)))
                hash[name] = MediaStore.Images.Media.getBitmap(contentResolver, parse(photoUri))
            }
            contactsName.add(name)
            contactsPhone.add(number)
        }
        info.sort()
        if (contactsName.size > 0) {
            contactsItemList.layoutManager = LinearLayoutManager(this)
            contactsItemList.adapter = ContactsItemListAdapter(this, info)
        }


    }
}
