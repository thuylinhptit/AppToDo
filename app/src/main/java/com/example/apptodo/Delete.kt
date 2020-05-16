package com.example.apptodo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.fragment.app.DialogFragment



class Delete : DialogFragment() {
    lateinit var congViecdelete: CongViec
    lateinit var listData: ListData
    lateinit var adapterdelete : ListCVAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_fire_missiles)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("Delete","d")
                        listData.db.removeItem(congViecdelete.id)
                        adapterdelete.deleteCongViec(congViecdelete)
                       // listData.dsHomeFragment.remove(congViecdelete)
                        dismiss()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

