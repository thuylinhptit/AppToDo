package com.example.apptodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ImportantFragment : Fragment() {

    lateinit var rcvFav: RecyclerView
    lateinit var adapter: ListCVAdapter
    val listData = ListData
    val clickDone: (CongViec) -> Unit = {}
    val clickLike: (CongViec) -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_important, container, false)

        rcvFav = view.findViewById(R.id.rcvfav)

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

        rcvFav.apply {
            adapter = this@ImportantFragment.adapter
            layoutManager = LinearLayoutManager(this@ImportantFragment.context)
        }

        adapter.updateData(ListData.dsFavFragment)
        adapter.onClickLike = {
            adapter.like(it)
            listData.db.updateIteam(it.id, it)
            listData.dsFavFragment.remove(it)
            listData.dsHomeFragment.add(it)
            listData.db.updateIteam(it.id, it)
            Toast.makeText( context, "No Important", Toast.LENGTH_LONG).show()
        }

        adapter.onClickDone = {
            adapter.done(it)
            listData.db.updateIteam(it.id, it)
            listData.dsFavFragment.remove(it)
            listData.dsHomeFragment.remove(it)
            if( it.isCheck == "1" ){
                clickDone(it)
                listData.dsDoneFragment.add(it)
                listData.db.updateIteam(it.id, it)
                Toast.makeText(context, "DONE!!", Toast.LENGTH_LONG).show()
            }
            else{
                listData.dsDoneFragment.remove(it)
            }
        }
        return view
    }
    fun delete( congViec: CongViec ){
        adapter.deleteCongViec(congViec)
        listData.dsFavFragment.remove(congViec)
    }

}
