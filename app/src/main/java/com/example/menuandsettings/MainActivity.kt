package com.example.menuandsettings
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.view.isVisible
import com.example.menuandsettings.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var peopleList:List<Registration> =ArrayList()
    lateinit var background:LinearLayout
    lateinit var clocklayout:LinearLayout
    lateinit var tClock:TextClock
    lateinit var itemRed: MenuItem
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          background=findViewById<LinearLayout>(R.id.backMenu) as LinearLayout
        //clocklayout=findViewById<LinearLayout>(R.id.textClockLinearLayoutID) as LinearLayout
          //tClock=findViewById<TextClock>(R.id.textClock) as TextClock
       //binding= ActivityMainBinding.inflate(layoutInflater)
       // itemRed=findViewById<LinearLayout>(R.id.redColor) as MenuItem
        supportActionBar?.title="Option Menu"
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
                setContentView(R.layout.registerlayout)
                Toast.makeText(this@MainActivity,"Register options",Toast.LENGTH_SHORT).show()
                //val intent=Intent(this,RegisterActivity::class.java)
                //startActivity(intent)
            }


            //R.id.Register->background.setBackgroundColor(Color.parseColor("#00FF00"))
            R.id.Login->{
                setContentView(R.layout.loginlayout)
                //background.setBackgroundColor(Color.parseColor("#0000FF"))
            }
            R.id.textsome->{Toast.makeText(this@MainActivity,"This item will be shown always on the action bar"
            ,Toast.LENGTH_SHORT).show()
                setContentView(R.layout.activity_main)

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
       // val view1:View=setContentView(R.layout.listregpeopleview,null)
       // data
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
            val view:View=inflater.inflate(R.layout.registerlayout,null)
           var name1=view.findViewById<EditText>(R.id.editname)
            var email1=view.findViewById<EditText>(R.id.editemail)
            var pass1=view.findViewById<EditText>(R.id.editpasword)
            peoplelist[p0].name= name1.toString()
            peoplelist[p0].email=email1.toString()
            peoplelist[p0].password=pass1.toString()

        return view
        }

    }
}