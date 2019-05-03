package com.example.contactmanager

import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_contact_info.*

class ContactInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
        phone.setText(intent.getStringExtra("phone"))


        paytm.setText(intent.getStringExtra("phone"))

        whatsapp.setText(intent.getStringExtra("phone"))


        name.setText(intent.getStringExtra("name"))

        val cr = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)

        while (cr.moveToNext()){
            var number = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER ))
            if(number==intent.getStringExtra("phone")){
                var photoUri = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI ))
                if(photoUri!=null){
                    imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver,Uri.parse(photoUri)))
                }else{
                    imageView.setImageResource(R.drawable.profile)
                }
            }
        }

    }
}
