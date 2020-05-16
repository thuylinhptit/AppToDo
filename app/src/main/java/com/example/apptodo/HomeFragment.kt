package com.example.apptodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.nio.file.Files.delete


class HomeFragment : Fragment() {

    lateinit var rcvHome: RecyclerView
    lateinit var btnAdd: FloatingActionButton
    lateinit var adapter: ListCVAdapter
    val listData = ListData
    var addTodo = AddToDo()
    val clickLike: (CongViec) -> Unit = {}
    val clickDone: (CongViec) -> Unit = {}
   // var dialogDelete = Delete()
    //var onClickDelete : (CongViec) -> Unit ={}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)
        rcvHome = view.findViewById(R.id.rcvHome)
        btnAdd = view.findViewById(R.id.btnAdd)
        listData.db = SQLite(requireContext())

        btnAdd.setOnClickListener {
            addTodo.show(childFragmentManager, null)
            addTodo.clickAdd = {
                listData.db.addToDo(listOf(it))
                listData.dsHomeFragment.add(it)
                adapter.addCongViec(it)
                Toast.makeText(context, "Add Success!!", Toast.LENGTH_LONG).show()
                addTodo.dismiss()

            }
        }

        adapter = ListCVAdapter(
            {
                this.context
            },
            {
               delete(it)
            },
            {
                clickDone(it)
            },
            {
                clickLike(it)
            }
        )

        rcvHome.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }

        adapter.updateData(ListData.dsHomeFragment)

        adapter.onClickLike = {
            adapter.like(it)
            listData.db.updateIteam(it.id, it)
            if (it.isLike == "1") {
                clickLike(it)
                listData.dsFavFragment.add(it)
                listData.dsHomeFragment.remove(it)
                Toast.makeText(context, "Important ❤", Toast.LENGTH_LONG).show()
            } else listData.dsFavFragment.remove(it)
            listData.db.updateIteam(it.id, it)
        }

        adapter.onClickDone = {
            adapter.done(it)
            listData.db.updateIteam(it.id, it)
            if (it.isCheck == "1" ) {
                clickDone(it)
                listData.dsDoneFragment.add(it)
                listData.dsHomeFragment.remove(it)
                Log.d("AAA","2")
                Toast.makeText(context, "DONE!!", Toast.LENGTH_LONG).show()
            } else {
                listData.dsDoneFragment.remove(it)
            }
            listData.db.updateIteam(it.id, it)
        }

//        adapter.onLongClickItem= {
//            dialogDelete.show(childFragmentManager, null)
//            dialogDelete.congViecdelete= it
//            dialogDelete.adapterdelete = adapter
//        }
        return view
    }
    fun delete( congViec: CongViec ){
        adapter.deleteCongViec(congViec)
        listData.dsHomeFragment.remove(congViec)
    }

}