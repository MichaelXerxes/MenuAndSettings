package com.example.menuandsettings
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import com.example.menuandsettings.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.Console


class MainActivity : AppCompatActivity() {
    private var peopleList:List<Registration> =ArrayList()
    lateinit var background:LinearLayout
    lateinit var clocklayout:LinearLayout
    lateinit var tClock:TextClock
    lateinit var itemRed: MenuItem
   // lateinit var binding: ActivityMainBinding

    lateinit var idedit:EditText
    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var buttonregister:Button
    lateinit var db:RegistrationAdapter

    lateinit var reglayout:View
    lateinit var Savebutton:Button
    lateinit var Viewbutton:Button
    // val website:WebView=findViewById(R.id.webID)

    //lateinit var viewReg:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val website:WebView=findViewById(R.id.webID)
       // website.loadUrl("https://www.google.co.uk")


        setContentView(R.layout.activity_main)

        Savebutton=findViewById(R.id.buttonSave)
        Viewbutton=findViewById(R.id.buttonView)

       // setupListofDataIntoRecyclerView()

        //val bottomSheet:View=findViewById(R.id.bottom_sheet,null)
        //val buttonSheetCall:Button=findViewById(R.id.button_forsheet)
        //val bottomSheetBehavior:BottomSheetBehavior<View> =BottomSheetBehavior.from(bottomSheet)
       /* bottomSheetBehavior.setBottomSheetCallback(object :BottomSheetBehavior.BottomSheetCallback()
        {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Toast.makeText(applicationContext,"OnSlide Function Fiu FIu ",Toast.LENGTH_SHORT).show()
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_DRAGGING->Toast.makeText(applicationContext,"Bottom Shett Behavior State dragginf",Toast.LENGTH_SHORT).show()

                }
            }
        })*/

         // background=findViewById<LinearLayout>(R.id.backMenu) as LinearLayout
        db= RegistrationAdapter(this)

        supportActionBar?.title="Option Menu"
        Savebutton.setOnClickListener {
            saveRecord()
            return@setOnClickListener
        }
        Viewbutton.setOnClickListener {
            viewRecord()
            Toast.makeText(applicationContext, "View View", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
      /*  buttonSheetCall.setOnClickListener {
            if(bottomSheetBehavior.state==BottomSheetBehavior.STATE_COLLAPSED)
            {
                bottomSheetBehavior.state=BottomSheetBehavior.STATE_EXPANDED
            }
            else
            {
                bottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
            }
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true//super.onCreateOptionsMenu(menu)
        //var linearLayout:LinearLayout=findViewById(R.id.textClock)
       // val view1: View =layoutInflater.inflate(R.layout.textclock,null)


    }
    fun onGroupItemClick(item: MenuItem) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by <code><a href="/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem)">onOptionsItemSelected()</a></code>
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){



            R.id.Clock->{
                    setContentView(R.layout.textclock)
                    Toast.makeText(this@MainActivity,"Time",Toast.LENGTH_SHORT).show()
                }
            R.id.Register->{

                setContentView(R.layout.registerlayout)
                Toast.makeText(this@MainActivity,"Register options",Toast.LENGTH_SHORT).show()
              // addRecordRegistrations()
                buttonregister=findViewById(R.id.register_button)
                buttonregister.setOnClickListener {
                    saveRecord()
                    setContentView(R.layout.listregpeopleview)
                    viewRecord()
                }

            }

            R.id.Login->{
                setContentView(R.layout.loginlayout)
            }
            R.id.textsome->{
                Toast.makeText(this@MainActivity,"This item will be shown always on the action bar"
            ,Toast.LENGTH_SHORT).show()
               setContentView(R.layout.activity_main)

            }
            R.id.menu_sheetbottom->{
                Toast.makeText(this@MainActivity,"hahahahah",Toast.LENGTH_SHORT).show()
                setContentView(R.layout.bottom_sheet)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.title=="redColor")
            setContentView(R.layout.textclock)
        else Toast.makeText(this@MainActivity,"Red color Click",Toast.LENGTH_SHORT).show()
        return true//super.onContextItemSelected(item)
    }

    override fun onActionModeStarted(mode: ActionMode?) {
        setContentView(R.layout.textclock)
        super.onActionModeStarted(mode)
    }
    fun  saveRecord(){

        name=findViewById(R.id.editname)
        email=findViewById(R.id.editemail)
        password=findViewById(R.id.editpasword)
        idedit=findViewById(R.id.editID)

       // val idNew= findViewById<EditText>(R.id.edituser_ID)
      //  val nameNew= findViewById<EditText>(R.id.edituser_Name)
        //val emailNew=findViewById<EditText>(R.id.edituser_Email)
       // val passNew=findViewById<EditText>(R.id.edituser_Pass)

     //   val id=idedit.text.toString()
      //  val name1=name.text.toString()
      //  val email1=email.text.toString()
     //   val pass=password.text.toString()
        val id12=idedit.text.toString()
        val name12=name.text.toString()
        val email12=email.text.toString()
        val pass12=password.text.toString()
        val dbHandler:RegistrationAdapter=RegistrationAdapter(this)
        Log.println(Log.VERBOSE,"Errrrr","ID- $id12 Name- $name12 EM- $email12 PASS- $pass12  ")
        if(id12.trim()!=""&& name12.trim()!=""&& email12.trim()!="" && pass12.trim()!=""){
            val status=dbHandler.insertDATA(Registration(Integer.parseInt(id12),name12,email12,pass12))
            if(status>-1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
              //  idNew.text.clear()
               // nameNew.text.clear()
              //  passNew.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }


      //  dbHandler.insertDATA(Registration(Integer.parseInt(id),name1,email1,pass))
       // idedit.text.clear()
       // name.text.clear()
       // email.text.clear()
       // password.text.clear()
    }

    fun viewRecord(){

        val dbHandler:RegistrationAdapter= RegistrationAdapter(this)
        val regList:List<Registration> =dbHandler.readtDATA()
        val regListID=Array<String>(regList.size){"0"}
        val regListName=Array<String>(regList.size){"null"}
        val regListEmail=Array<String>(regList.size){"null"}
        val regListPassword=Array<String>(regList.size){"null"}
        var index=0
        val listView=findViewById<ListView>(R.id.data_list1)

        for (x in regList){
            regListID[index]=x.id.toString()
            regListName[index]=x.name
            regListEmail[index]=x.email
            regListPassword[index]=x.password
            index++
        }

        val myListAdapter=MyListAdapter(this,regListID,regListName,regListEmail,regListPassword)

        listView.adapter=myListAdapter



    }
    private fun addRecordRegistrations(){
        //val name:EditText=findViewById(R.id.editname)
       buttonregister=findViewById(R.id.register_button)
       reglayout=findViewById(R.id.layout_image)
      //  name=findViewById(R.id.editname)
    //    email=findViewById(R.id.editemail)
      //  password=findViewById(R.id.editpasword)
      //  idedit=findViewById(R.id.editID)
      //  name=findViewById(R.id.editname)
       // email=findViewById(R.id.editemail)
       // password=findViewById(R.id.editpasword)
      //  idedit=findViewById(R.id.editID)

        buttonregister.setOnClickListener {
            if(name.text.toString().isEmpty()||email.text.toString().isEmpty()||password.text.toString().isEmpty()){
                setContentView(R.layout.loginlayout)
                Toast.makeText(this@MainActivity,"Fill all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else {
                //saveRecord()
                val idlocal=idedit.text.toString().toInt()
                val namelocal = name.text.toString()
                val emaillocal = email.text.toString()
                val passlocal = password.text.toString()
                Log.println(Log.VERBOSE,"Name","$namelocal  $emaillocal    $passlocal")
                val data = Registration( idlocal,namelocal, emaillocal,passlocal)


                db.insertDATA(data)
                //val list=db.readtDATA()

                Log.println(Log.VERBOSE,"toString Reg"," $namelocal  $emaillocal    $passlocal ")
                Toast.makeText(this@MainActivity, "data saved successfully", Toast.LENGTH_SHORT).show()
                idedit.text.clear()
                name.text.clear()
                email.text.clear()
                password.text.clear()

                setContentView(R.layout.listview2)

            }

            //setContentView(R.layout.loginlayout)
        }
    }
    private fun getItemsList():ArrayList<Registration>{
        val dbHandler:RegistrationAdapter= RegistrationAdapter(this)
        val regList:ArrayList<Registration> =dbHandler.readtDATA()
        return regList
    }
    private fun setupListofDataIntoRecyclerView(){ //not used method
        val rvItemsList:RecyclerView=findViewById(R.id.rvItemsList2)
        //val tvNoRecordsAvailable:TextView=findViewById(R.id.tvNoRecordsAvailable)
       // val textID:TextView=findViewById(R.id.viewID2)
       // val textName:TextView=findViewById(R.id.viewName2)
       // val textEmail:TextView=findViewById(R.id.viewEmail2)
       // val textPass:TextView=findViewById(R.id.viewPass2)
        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            //tvNoRecordsAvailable.visibility = View.GONE


            rvItemsList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ItemAdapter(this, getItemsList())

            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
           // tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
 // class not used atm...
 /*
    class PeopleAdapter(internal var activity: Activity,internal var peoplelist:List<Registration>):BaseAdapter(){
        internal var inflater:LayoutInflater
        init {
            inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        override fun getCount(): Int {
            return peoplelist.size
        }

        override fun getItem(p0: Int): Any {
            return peoplelist[p0]
        }

        override fun getItemId(p0: Int): Long {
            return peoplelist[p0].id.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val view3:View=inflater.inflate(R.layout.registerlayout,null)
            //var id3:Int=-7
           var name1=view3.findViewById<EditText>(R.id.editname)
            var email1=view3.findViewById<EditText>(R.id.editemail)
            var pass1=view3.findViewById<EditText>(R.id.editpasword)
            //peoplelist[p0].id=id3
            peoplelist[p0].name= name1.toString()
            peoplelist[p0].email=email1.toString()
            peoplelist[p0].password=pass1.toString()

        return view3
        }
        //fun readData():Cursor{
       //     val db:SQLiteDatabase=this.get
      //  }

    }*/
}