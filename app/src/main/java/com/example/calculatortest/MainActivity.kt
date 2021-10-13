package com.example.calculatortest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    var operator = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true //super.onCreateOptionsMenu(menu)
    }

    //Menu functionality
    //Pop up with text (Toast)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id){
            R.id.menu_language -> {
                Toast.makeText(this, "Language: Kotlin", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_complete -> {
                Toast.makeText(this, "Completed: October 11", Toast.LENGTH_LONG).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }

        }


    }

    fun onDigit(view: View) { //displays numbers in input textView
        if (view is Button) {
            input.append(view.text)
        }
    }


    //Extra button functions
    fun clearAction(view: android.view.View) {
        input.text = ""
        results.text = ""
    }

    fun backspaceAction(view: android.view.View) {
        input.text = input.text.subSequence(0, input.text.length - 1)
    }

    fun decimalAction(view: android.view.View) {
        input.append(".")
    }

    fun ansAction(view: View){
        if (results.text.contains('-')){
            input.setText("No negative numbers")
        }else {
            input.text = results.text
            results.text = ""
        }

    }

    //Add operation to the input textview

    fun addAction(view: android.view.View) {
        input.append("+")
        operator++;
    }
    fun subtractAction(view: android.view.View) {
        input.append("-")
        operator++;
    }

    fun multAction(view: android.view.View) {
        input.append("x")
        operator++;
    }

    fun divideAction(view: android.view.View) {
        input.append("÷")
        operator++;
    }

    //Called when equals button is clicked
    //Need to turn the input into a list
    fun evaluateAction(view: android.view.View) {

        // checkOperator(input);
       // results.text = operator.toString()

        val list = mutableListOf<Any>()
        var current = ""
        for (character in input.text) {

            if (character.isDigit() || character == '.') { //This will allow floating point numbers to be added to the list correctly
                current += character
            } else {
                list.add(current.toFloat())
                current = ""
                list.add(character)
            }
        }
        if (current != "")
            list.add(current.toFloat())


        //results.text = list.toString() //checks what list looks like


        for (i in list.indices){ //checks for operation

            var previous: Float
            //computes operation
            if (list[i] == '+'){
                previous = list[i-1] as Float
                val next = list[i+1] as Float
                results.text = previous.plus(next).toString()
            }
            if (list[i] == '-'){
                previous = list[i-1] as Float
                val next = list[i+1] as Float
                results.text = previous.minus(next).toString()
            }
            if (list[i] == 'x'){
                previous = list[i-1] as Float
                val next = list[i+1] as Float
                results.text = previous.times(next).toString()
            }
            if (list[i] == '÷'){
                previous = list[i-1] as Float
                val next = list[i+1] as Float
                results.text = previous.div(next).toString()
            }


        }



    }

    //Error Checks

    fun checkOperator(textView: TextView){
        //Need to check if there is more than one operator in the list
        if (operator > 1){
            results.text = "One operation";
        }
    }

}

