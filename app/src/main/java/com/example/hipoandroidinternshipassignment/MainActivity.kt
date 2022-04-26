package com.example.hipoandroidinternshipassignment
/*
* Ali Ihsan Basoglu
* HipoLabs Summer Internship Assignment
* Android Kotlin Project
*
* */
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object{

        lateinit var memberAdapter : MemberAdapter
        lateinit var jsonobj : JSONObject
        var displayList = mutableListOf<Members>()

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fetch JSON and add them into list
        val obj = JSONObject(getJSONFromAssets()!!)
        val membersArray = obj.getJSONArray("members")
        jsonobj = obj

        for(i in 0 until membersArray.length()){
            val member = membersArray.getJSONObject(i)
            val name = member.getString("name")
            val age = member.getInt("age")
            val location = member.getString("location")
            val github = member.getString("github")
            val hipo = member.getJSONObject("hipo")
            val years_in_hipo = hipo.getInt("years_in_hipo")
            val position = hipo.getString("position")

            val memberFromJSON = Members(name, age, github, location, position, years_in_hipo)
            Members.myMemberList.add(memberFromJSON)

        }


        displayList.addAll(Members.myMemberList)

        memberAdapter = MemberAdapter(displayList)
        var rvMemberItems = findViewById<RecyclerView>(R.id.rvMemberItems)

        rvMemberItems.adapter = memberAdapter
        rvMemberItems.layoutManager = LinearLayoutManager(this)


        val myButton  = findViewById<Button>(R.id.btnAddMember)

        myButton.setOnClickListener {
            var dialog = DialogClass()
            dialog.show(supportFragmentManager , "customDialog")
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)

        val search: MenuItem? = menu?.findItem(R.id.menu_search)
        val searchView : SearchView = search?.actionView as SearchView

        searchView.queryHint = "Search Member.."


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                if(newText!!.isNotEmpty()){
                    displayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    Members.myMemberList.forEach{
                        if(it.name.toLowerCase(Locale.getDefault()).contains(search)){
                            displayList.add(it)
                        }
                    }
                    memberAdapter!!.notifyDataSetChanged()

                }
                else{
                    displayList.clear()
                    displayList.addAll(Members.myMemberList)
                    memberAdapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    // Function for reading JSON file from assets
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("hipo.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
