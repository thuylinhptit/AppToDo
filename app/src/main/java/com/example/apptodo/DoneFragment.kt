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
import kotlinx.android.synthetic.main.fragment_done.*

class DoneFragment : Fragment() {
    lateinit var rcvDone: RecyclerView
    lateinit var adapter: ListCVAdapter
    val listData = ListData
    val clickDone: (CongViec) -> Unit = {}
    val clickLike : (CongViec) -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_done, container, false)

        rcvDone = view.findViewById(R.id.rcvdone)
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

        rcvDone.apply {
            adapter = this@DoneFragment.adapter
            layoutManager = LinearLayoutManager(this@DoneFragment.context)
        }

        adapter.updateData(ListData.dsDoneFragment)
        adapter.onClickDone = {
            adapter.done(it)
            listData.db.updateIteam(it.id, it)
            listData.dsDoneFragment.remove(it)
            Toast.makeText(context, "Not DONE", Toast.LENGTH_LONG).show()
            listData.db.updateIteam(it.id, it)
        }
        adapter.onClickLike = {
            adapter.like(it)
            listData.db.updateIteam(it.id,it)
            if( it.isLike == "1" ){
                clickLike(it)
                Toast.makeText(context, "Important", Toast.LENGTH_LONG).show()
            }
            else{
                listData.dsFavFragment.remove(it)
                Toast.makeText(context,"Not Important ", Toast.LENGTH_LONG).show()
            }
            listData.db.updateIteam(it.id, it)
        }
        return view
    }

    fun delete( congViec: CongViec ){
        adapter.deleteCongViec(congViec)
        listData.dsDoneFragment.remove(congViec)
    }
}
