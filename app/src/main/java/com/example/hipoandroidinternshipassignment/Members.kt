package com.example.hipoandroidinternshipassignment

import org.json.JSONObject

data class Members (val name: String,
                    val age : Int,
                    val github : String,
                    val location : String,
                    val position: String,
                    val years_in_hipo : Int ) {

    companion object{
        var myMemberList = mutableListOf<Members>()

    }

}