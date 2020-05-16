package com.example.apptodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddToDo : DialogFragment() {
    lateinit var btnadd: Button
    lateinit var btncancel: Button
    lateinit var editText: EditText
    var clickAdd: (CongViec) -> Unit ={}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_add_to_do, container, false)
        btnadd = v.findViewById(R.id.btn_ok)
        btncancel = v.findViewById(R.id.btn_cancel)
        editText = v.findViewById(R.id.writeToDo)
        btnadd.setOnClickListener (object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (editText.text.toString().trim().length > 0) {
                    clickAdd(CongViec(editText.text.toString(), "0","0"))
                    editText.setText("")
                }
            }
        })

        btncancel.setOnClickListener (object : View.OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }
        })
        return v
    }

}