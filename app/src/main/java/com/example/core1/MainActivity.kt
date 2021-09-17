package com.example.core1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.view.View


//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    private var txtColor = Color.RED
    var opResult: Int =0
    var operator ="plus"
    //private val tally //= findViewById<TextView>(R.id.tally);
    //private val stealBtn = findViewById<Button>(R.id.stealBtn)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "onCreate")
        //app values
        val tally = findViewById<EditText>(R.id.tallys)
        val scoreBtn = findViewById<Button>(R.id.score_btn)
        val stealBtn = findViewById<Button>(R.id.stealBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        var sound = MediaPlayer.create(this,R.raw.clown_horn)
        tally.setText(getString(R.string.score_text)+opResult)
        //saved instance state
        savedInstanceState?.let{
            opResult =  savedInstanceState.getInt("SCORE")
            txtColor = savedInstanceState.getInt("COLOR")
            tally.setText(opResult.toString())
            tally.setTextColor(txtColor)
        }

        //onclicks
        scoreBtn.setOnClickListener {
            if(opResult == 14){//clicking on
                score()
                tally.setText(getString(R.string.winner_text))
                sound.start()
                scoreBtn.visibility = View.GONE
                stealBtn.visibility = View.GONE
                //now use cannot click to score
            }else {
                score()
                check()
                tally.setTextColor(txtColor)
                tally.setText(getString(R.string.score_text)+opResult)
            }
        }
        stealBtn.setOnClickListener {
            steal()
            check()
            tally.setTextColor(txtColor)
            tally.setText(getString(R.string.score_text)+opResult)
        }
        resetBtn.setOnClickListener {
            reset()
            tally.setTextColor(Color.RED)
            tally.setText(getString(R.string.score_text)+opResult)
            scoreBtn.visibility = View.VISIBLE
            stealBtn.visibility = View.VISIBLE
            sound.stop()
        }


    }

   override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SCORE",opResult)
        outState.putInt("COLOR",txtColor)
        Log.i("LIFECYCLE","saveInstanceState $opResult")
        Log.i("LIFECYCLE","saveInstanceState $txtColor")
    }
    //
    private fun check(){
            when (opResult){
                in 0..4 -> txtColor = Color.RED
                in 5..9 -> txtColor = Color.BLUE
                in 10..15 ->txtColor = Color.GREEN
            }
            Log.i("GAMESTATE","Game ongoing")

    }
    private fun score(){
        opResult ++

    }
    private fun steal(){
        if (opResult != 0){
            opResult --
        }
    }

    private fun reset(){
        opResult =0
    }
}