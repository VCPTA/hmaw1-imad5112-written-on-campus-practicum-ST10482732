package vcmsa.ci.imad5112

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var songTitles = ArrayList<String>()
    var artistNames = ArrayList<String>()
    var ratings = ArrayList<Int>()
    var comments = ArrayList<String>()

    var songEditText: EditText? = null
    var artistEditText: EditText? = null
    var ratingEditText: EditText? = null
    var commentEditText: EditText? = null
    var addButton: Button? = null
    var nextButton: Button? = null
    var exitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        songEditText = findViewById<View>(R.id.songEditText) as EditText
        artistEditText = findViewById<View>(R.id.artistEditText) as EditText
        ratingEditText = findViewById<View>(R.id.ratingEditText) as EditText
        commentEditText = findViewById<View>(R.id.commentEditText) as EditText
        addButton = findViewById<View>(R.id.addButton) as Button
        nextButton = findViewById<View>(R.id.nextButton) as Button
        exitButton = findViewById<View>(R.id.exitButton) as Button

        addButton!!.setOnClickListener {
            val songTitle = songEditText!!.text.toString()
            val artistName = artistEditText!!.text.toString()
            val rating = ratingEditText!!.text.toString()
            val comment = commentEditText!!.text.toString()

            if (songTitle.isEmpty() || artistName.isEmpty() || rating.isEmpty() || comment.isEmpty()) {
                Toast.makeText(this, "Please enter the details for the playlist", Toast.LENGTH_SHORT).show()
            } else {
                val rating = ratingEditText!!.toString().toInt()
                if (rating == null || rating < 1) {
                    Toast.makeText(this, "The average rating must be (1 or more)", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    songTitles.add(songTitle)
                    artistNames.add(artistName)
                    ratings.add(rating)
                    comments.add(comment)

                    Toast.makeText(this, "Details added successfully", Toast.LENGTH_SHORT).show()

                    songEditText!!.text.clear()
                    artistEditText!!.text.clear()
                    ratingEditText!!.text.clear()
                    commentEditText!!.text.clear()

                    nextButton!!.setOnClickListener{
                        var intent = Intent(this,SecondActivity::class.java)
                        intent.putStringArrayListExtra("songTitle", songTitles)
                        intent.putStringArrayListExtra("artistName", artistNames)
                        intent.putIntegerArrayListExtra("rating", ratings)
                        intent.putStringArrayListExtra("comment", comments)
                        startActivity(intent)
                    }
                    exitButton!!.setOnClickListener{
                        finishAffinity()
                    }

                }
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}




