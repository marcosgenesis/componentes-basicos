package com.example.t1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.Toast

import android.widget.ListView
import android.widget.ArrayAdapter
import com.example.t1.adapters.GridViewAdapter


class MainActivity : AppCompatActivity() {
    var siiuSound: MediaPlayer ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        siiuSound = MediaPlayer.create(this, R.raw.sii)


        addListenerOnButton()
        editText()
        autoComplete()
        spinner()
        longClickFun()
        listViewFun()
        listViewWithAdapterFun()
        gridViewFun()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menucontext,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about -> Toast.makeText(this,"About Selected",Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this,"Settings Selected",Toast.LENGTH_SHORT).show()
            R.id.exit -> Toast.makeText(this,"Exit Selected",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addListenerOnButton() {
        val toggleButton1 = findViewById<View>(R.id.toggleButton1) as ToggleButton
        val toggleButton2 = findViewById<View>(R.id.toggleButton2) as ToggleButton
        val btnDisplay = findViewById<View>(R.id.btnDisplay) as Button
        btnDisplay.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val result = StringBuffer()
                result.append("toggleButton1 : ").append(toggleButton1.getText())
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText())
                Toast.makeText(
                    this@MainActivity, result.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun editText(){
        val showButton = findViewById<Button>(R.id.showInput)
        val editText = findViewById<EditText>(R.id.editText)

        showButton.setOnClickListener {
            val text = editText.text
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun autoComplete(){
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val languages = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, languages)
        autotextView.setAdapter(adapter)

    }

    private fun spinner(){
        val languages = resources.getStringArray(R.array.Languages)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    fun radioButton() {
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(
                    applicationContext, " On checked change :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
            })

    }

    fun radioBtnClick(view: View) {

        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
        Toast.makeText(
            applicationContext, "On click : ${radio.text}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun popMenu(view: View){
        val button1 = findViewById<View>(R.id.longclick) as Button
        button1.setOnClickListener(View.OnClickListener {
            //Creating the instance of PopupMenu
            val popup = PopupMenu(this@MainActivity, button1)
            //Inflating the Popup using xml file
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    Toast.makeText(
                        this@MainActivity,
                        "You Clicked : " + item.title,
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
            })
            popup.show() //showing popup menu
        }) //closing the setOnClickListener method
        val menu = PopupMenu(this, view)

        menu.menu.add("One")
        menu.menu.add("Two")
        menu.menu.add("Three")

        menu.show()
    }

    private fun longClickFun(){
        val button = findViewById<Button>(R.id.longclick);
        button.setOnLongClickListener(View.OnLongClickListener {
            Toast.makeText(
                applicationContext,
                "Voce está me pressionando 🥺", 2000
            ).show()
            true
        })
    }

    fun playSong(view:View){
        val btn = findViewById<Button>(R.id.siSong)
        btn.setOnClickListener {
            this.siiuSound?.start();
        }

    }

    private fun listViewFun(){
        val listview = findViewById<View>(R.id.listview) as ListView
        val dados = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dados)
        listview.setAdapter(adapter);

    }

    private fun listViewWithAdapterFun() {
        val arrayAdapter: ArrayAdapter<*>
        val dados = resources.getStringArray(R.array.Languages)

        val itemsAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, dados)

        var languagesListView = findViewById<ListView>(R.id.languagesListAdapter)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, dados)
        languagesListView.adapter = arrayAdapter
    }

    private fun gridViewFun(){
        lateinit var gridView: GridView
        var playerNames = arrayOf("Cristiano Ronaldo", "Joao Felix", "Bernado Silva", "Andre    Silve", "Bruno Fernandez", "William Carvalho", "Nelson Semedo", "Pepe", "Rui Patricio")

        gridView = findViewById(R.id.gridView)
        val mainAdapter = GridViewAdapter(this@MainActivity, playerNames)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(applicationContext, "Voce clicou em " + playerNames[+position],
                Toast.LENGTH_SHORT).show()
        }
    }
}