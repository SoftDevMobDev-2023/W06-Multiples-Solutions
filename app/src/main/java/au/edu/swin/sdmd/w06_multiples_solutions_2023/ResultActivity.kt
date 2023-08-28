package au.edu.swin.sdmd.w06_multiples_solutions_2023

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Result>("result", Result::class.java)
        } else {
            intent.getParcelableExtra<Result>("result")
        }
        val tvResult = findViewById<TextView>(R.id.result)
        tvResult.text = result?.opResult.toString()

        val bCorrect = findViewById<Button>(R.id.correct)
        val bIncorrect = findViewById<Button>(R.id.incorrect)
        bCorrect.setOnClickListener { correctAnswer(true) }
        bIncorrect.setOnClickListener { correctAnswer(false) }

    }

    fun correctAnswer(answer: Boolean) {
        val i = Intent()
        i.putExtra("correct", answer)
        setResult(Activity.RESULT_OK, i)
        this.finish()
    }

}