package com.example.contactmanager

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import android.service.autofill.TextValueSanitizer
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.net.URI

class ContactsItemListAdapter(var context:Context,
                              var contacts:ArrayList<String>) : RecyclerView.Adapter<ContactsItemListAdapter.ContactsViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactsViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.contact_item,p0,false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(p0: ContactsViewHolder, p1: Int) {
        var name = contacts[p1].split(".")
        p0.contactsName.setText(name[0])
        p0.contactPhone.setText(name[1])
        p0.contactLetter.setText(name[0][0].toString())
        p0.parentLayout.setOnClickListener {
            var intent:Intent = Intent(context,ContactInfo::class.java)
            intent.putExtra("name",name[0])
            intent.putExtra("phone",name[1])
            context.startActivity(intent)
        }
    }

    inner class ContactsViewHolder(var view:View):RecyclerView.ViewHolder(view){
        var contactsName: TextView = view.findViewById(R.id.contactName)
        var contactLetter:TextView = view.findViewById(R.id.circularImageView)
        var contactPhone:TextView = view.findViewById(R.id.contactPhone)
        var parentLayout:ConstraintLayout = view.findViewById(R.id.contactItemLayout)
    }
}