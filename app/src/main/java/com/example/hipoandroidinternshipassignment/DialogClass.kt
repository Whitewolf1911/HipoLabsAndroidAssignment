package com.example.hipoandroidinternshipassignment

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.lang.reflect.Member
import java.nio.charset.Charset


class DialogClass : DialogFragment(){


    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       var rootView : View = inflater.inflate(R.layout.layout_dialog, container, false)

       rootView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
           dismiss()
       }
        rootView.findViewById<Button>(R.id.btn_add).setOnClickListener {

            //Getting user input adding new member to UI
            val position = "Intern"
            val years = 0
            val name = rootView.findViewById<EditText>(R.id.editName).text.toString()
            val github = rootView.findViewById<EditText>(R.id.editGithub).text.toString()
            val location = rootView.findViewById<EditText>(R.id.editLocation).text.toString()
            val age = rootView.findViewById<EditText>(R.id.editAge).text.toString()
            val ageToInt = age.toInt()

            val newMember = Members(name, ageToInt ,github, location, position , years)

            Members.myMemberList.add(newMember)
            MainActivity.displayList.add(newMember)
            MainActivity.memberAdapter.notifyDataSetChanged()

            //// Code for updating JSON file (Cancelled)

//            val path = "hipo.json"
//            var hipoObj = JSONObject()
//            var mainJSONObj = JSONObject()
//            val gson = Gson()
//            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
//
//            hipoObj.put("position" , position)
//            hipoObj.put("years_in_hipo" , years)
//
//
//            var jsonNewMember = JSONObject()
//            jsonNewMember.put("name", name)
//            jsonNewMember.put("age", age)
//            jsonNewMember.put("location", location)
//            jsonNewMember.put("github", github)
//            jsonNewMember.put("hipo", hipoObj)
//
//            mainJSONObj.put("company" , "Hipo")
//            mainJSONObj.put( "team" ,  "Android")
//
//            val jsonMembersArray = MainActivity.jsonobj.getJSONArray("members")
//            jsonMembersArray.put(jsonNewMember)
//            mainJSONObj.put( "members" ,  jsonMembersArray)
//
//            val prettyJsonObject : String = gsonPretty.toJson(mainJSONObj)
//            Log.d("TEST  ",prettyJsonObject)

           // File("hipo.json").writeText(prettyJsonObject) // that's not gonna work !!!


            //
            dismiss()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWidthPercent(85)
    }

}