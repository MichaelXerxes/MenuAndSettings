package com.example.menuandsettings
import android.content.Intent
import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextClock
import androidx.core.view.isVisible
import com.example.menuandsettings.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
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
}