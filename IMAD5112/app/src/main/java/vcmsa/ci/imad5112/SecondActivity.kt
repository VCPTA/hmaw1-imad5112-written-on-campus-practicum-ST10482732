package vcmsa.ci.imad5112

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

     var songTitles: ArrayList<String>? = null
     var artistNames: ArrayList<String>? = null
    var ratings: ArrayList<Int>? = null
    var comments: ArrayList<String>? = null

    var resultsTextView: TextView? = null
    var displayButton: Button? = null
    var ratingButton: Button? = null
    var mainScreenButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        resultsTextView = findViewById<View>(R.id.resultsTextView) as TextView
        displayButton = findViewById<View>(R.id.displayButton) as Button
        ratingButton = findViewById<View>(R.id.ratingButton) as Button
        mainScreenButton = findViewById<View>(R.id.mainScreenButton) as Button

        songTitles = intent.getStringArrayListExtra("songTitles")?: ArrayList()
        artistNames = intent.getStringArrayListExtra("artistNames")?: ArrayList()
        ratings = intent.getIntegerArrayListExtra("ratings")?: ArrayList()
        comments = intent.getStringArrayListExtra("comments")?: ArrayList()

        displayButton!!.setOnClickListener {
            var output = " "

            for ( i in songTitles!!.indices) {
                output += "song : ${songTitles!![i]}\n"
                output += "artist : ${artistNames!![i]}\n"
                output += "rating : ${ratings!![i]}\n"
                output += "comment : ${comments!![i]}\n\n"
            }
            resultsTextView!!.text = output
        }
        ratingButton!!.setOnClickListener {
            var output = " "

            for ( i in songTitles!!.indices) {
                if ( ratings!![i] >=2) {
                    output += "song : ${songTitles!![i]} - rating : ${ratings!![i]}\n"
                }
                resultsTextView!!.text = output
            }
            mainScreenButton!!.setOnClickListener {
                finish()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}