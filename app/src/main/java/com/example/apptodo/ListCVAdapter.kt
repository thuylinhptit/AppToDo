package com.example.apptodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCVAdapter(
    val context: () -> Context?,
    var onLongClickItem: (CongViec) -> Unit = {},
    var onClickDone: (CongViec) -> Unit={},
    var onClickLike: (CongViec) -> Unit={}
): RecyclerView.Adapter <ListCVAdapter.CongViecHolder>() {

    val listData: MutableList<CongViec> = mutableListOf<CongViec>()

    inner class CongViecHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.content)
        val btnDone: ImageButton = view.findViewById(R.id.btn_done)
        val btnLike: ImageButton = view.findViewById(R.id.btn_Like)

        fun bind(congviec: CongViec) {
            content.text = congviec.muctieu

            btnLike.setImageResource(
                if( congviec.isLike == "1" ) R.drawable.heart_outline_40px
                else R.drawable.heart_40px
            )
            btnDone.setImageResource(
                if( congviec.isCheck == "1" ) R.drawable.isckeck
                else R.drawable.notcheck
            )

            btnDone.setOnClickListener {
                onClickDone(congviec)
            }
            btnLike.setOnClickListener {
                onClickLike(congviec)
            }

            itemView.setOnLongClickListener {
                onLongClickItem(congviec)
                true
            }

        }
    }

    fun updateData(list: List<CongViec>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }


    fun like(it: CongViec) {
        val position = listData.indexOf(it)
        if( it.isLike == "0" ) it.isLike = "1"
        else it.isLike = "0"
        notifyDataSetChanged()
    }


    fun done(it: CongViec) {
        val position = listData.indexOf(it)
        if( it.isCheck == "0" ) it.isCheck = "1"
        else it.isCheck = "0"
        notifyDataSetChanged()
    }

    fun addCongViec(congviec: CongViec) {
        if( ! listData.contains(congviec )){
            listData.add(congviec)
            notifyItemChanged(listData.size - 1)
        }
    }

    fun deleteCongViec(congviec: CongViec) {
        listData.remove(congviec)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CongViecHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CongViecHolder(view)

    }

    override fun getItemCount(): Int {
        return listData.size
    }


    override fun onBindViewHolder(holder: CongViecHolder, position: Int) {
        holder.bind(listData[position])

    }

}