package com.example.menuandsettings
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*

import com.example.menuandsettings.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {
    private var peopleList:List<Registration> =ArrayList()
    lateinit var background:LinearLayout
    lateinit var clocklayout:LinearLayout
    lateinit var tClock:TextClock
    lateinit var itemRed: MenuItem
    lateinit var binding: ActivityMainBinding

    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var buttonregister:Button
    lateinit var db:RegistrationAdapter

    //lateinit var viewReg:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

          background=findViewById<LinearLayout>(R.id.backMenu) as LinearLayout
        db= RegistrationAdapter(this)
        //clocklayout=findViewById<LinearLayout>(R.id.textClockLinearLayoutID) as LinearLayout
          //tClock=findViewById<TextClock>(R.id.textClock) as TextClock
       //binding= ActivityMainBinding.inflate(layoutInflater)
       // itemRed=findViewById<LinearLayout>(R.id.redColor) as MenuItem
        supportActionBar?.title="Option Menu"
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

            //R.id.redColor->background.setBackgroundColor(Color.parseColor("#FF0000"))

                R.id.Clock->{
                    setContentView(R.layout.textclock)
                    Toast.makeText(this@MainActivity,"Time",Toast.LENGTH_SHORT).show()


                    //setBackgroundColor(Color.parseColor("#FF0000"))
                }
            R.id.Register->{
                //val viewReg:View= View.inflate(Context.)
                setContentView(R.layout.registerlayout)
                Toast.makeText(this@MainActivity,"Register options",Toast.LENGTH_SHORT).show()
                buttonregister=findViewById(R.id.register_button)
                name=findViewById(R.id.editname)
                email=findViewById(R.id.editemail)
                password=findViewById(R.id.editpasword)

                buttonregister.setOnClickListener {
                   if(name.text.toString().isEmpty()||email.text.toString().isEmpty()||password.text.toString().isEmpty()){
                       setContentView(R.layout.loginlayout)
                       Toast.makeText(this@MainActivity,"Fill all the fields",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                   }else {

                        val namelocal = name.text.toString()
                        val emaillocal = email.text.toString()
                        val passlocal = password.text.toString()
                        val data = Registration( 24,namelocal, emaillocal,passlocal)

                        //val bottomView:View=findViewById(R.id.layout_bottomsheet)
                        db.insertDATA(data)
                        Toast.makeText(this@MainActivity, "data saved successfully", Toast.LENGTH_SHORT).show()
                        name.text.clear()
                        email.text.clear()
                        password.text.clear()
                        setContentView(R.layout.textclock)

                    }
                }

            }



            R.id.Login->{
                setContentView(R.layout.loginlayout)

            }
            R.id.textsome->{
                Toast.makeText(this@MainActivity,"This item will be shown always on the action bar"
            ,Toast.LENGTH_SHORT).show()
                setContentView(R.layout.listview3)
                refreshDATA()

            }
            R.id.menu_sheetbottom->{
                Toast.makeText(this@MainActivity,"hahahahah",Toast.LENGTH_SHORT).show()
                setContentView(R.layout.bottom_sheet)
              // refreshDATA()

              // val bottomSheet:View=findViewById(R.id.bottom_sheet)
              //  val buttonSheetCall:Button=findViewById(R.id.button_forsheet)
               // val bottomSheetBehavior:BottomSheetBehavior<*> =BottomSheetBehavior.from<View>(bottomSheet)
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

                //background=findViewById<LinearLayout>(R.id.backMenu) as LinearLayout
                //db= RegistrationAdapter(this)
                //clocklayout=findViewById<LinearLayout>(R.id.textClockLinearLayoutID) as LinearLayout
                //tClock=findViewById<TextClock>(R.id.textClock) as TextClock
                //binding= ActivityMainBinding.inflate(layoutInflater)
                // itemRed=findViewById<LinearLayout>(R.id.redColor) as MenuItem

                /* buttonSheetCall.setOnClickListener {
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

    fun setBindings(binding: ActivityMainBinding){

    }

    fun refreshDATA(){
        val db=RegistrationAdapter(this)
        peopleList=db.readtDATA()
        val adapter=PeopleAdapter(this,peopleList)
        //viewlist=findViewById(R.id.listlayout)
        //viewlist.findViewById<ListView>(R.id.data_list).adapter=adapter
        val newList:ListView = findViewById(R.id.listview3ID)
        newList.adapter=adapter
    }

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
            return peoplelist[p0].email.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val view3:View=inflater.inflate(R.layout.registerlayout,null)
            var id3:Int=-1
           var name1=view3.findViewById<EditText>(R.id.editname)
            var email1=view3.findViewById<EditText>(R.id.editemail)
            var pass1=view3.findViewById<EditText>(R.id.editpasword)
            peoplelist[p0].id=id3
            peoplelist[p0].name= name1.toString()
            peoplelist[p0].email=email1.toString()
            peoplelist[p0].password=pass1.toString()

        return view3
        }
        //fun readData():Cursor{
       //     val db:SQLiteDatabase=this.get
      //  }

    }
}