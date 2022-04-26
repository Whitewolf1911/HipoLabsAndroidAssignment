package com.example.hipoandroidinternshipassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Member
import java.util.*
import kotlin.collections.ArrayList

class MemberAdapter (private val members: MutableList<Members>) :RecyclerView.Adapter<MemberAdapter.MembersViewHolder>()
{
    class MembersViewHolder (itemView : View): RecyclerView.ViewHolder(itemView)

//    fun addMember (member : Members){
//        members.add(member)
//        notifyItemInserted(members.size-1)
//    }

     var membersListFiltered =  mutableListOf<Members>()

    // Creates each member card
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        return MembersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.member_card, parent,  false)
        )
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        val currentMember = members[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.tvMemberCard).text = currentMember.name
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

//    override fun getFilter(): Filter {
//
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if (charSearch.isEmpty()) {
//                    membersListFiltered = Members.myMemberList
//                } else {
//                    val resultList = mutableListOf<Members>()
//                    for (it in Members.myMemberList) {
//                        if (it.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
//                            resultList.add(it)
//                        }
//                    }
//                    membersListFiltered = resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = membersListFiltered
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//
//                membersListFiltered = results?.values as MutableList<Members>
//                notifyDataSetChanged()
//            }
//        }}

}
