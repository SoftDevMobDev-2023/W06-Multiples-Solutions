package au.edu.swin.sdmd.w06_multiples_solutions_2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var factor1 = Random.nextInt(from = 1, until = 13)
        var factor2  = Random.nextInt(from = 1, until = 13)
        val tvFactor1 = findViewById<TextView>(R.id.factor1)
        val tvFactor2 = findViewById<TextView>(R.id.factor2)

        tvFactor1.text = factor1.toString()
        tvFactor2.text = factor2.toString()

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if(result.resultCode == RESULT_OK) {
                result.data?.let {
                    val c = if (it.getBooleanExtra("correct", false)) 1 else 0
                    correct += c
                    val tvCorrect = findViewById<TextView>(R.id.numCorrect)
                    tvCorrect.text = correct.toString()
                }
            }
        }

        val multiply = findViewById<Button>(R.id.multiply)
        multiply.setOnClickListener {
            val i = Intent(this, ResultActivity::class.java).apply {
                putExtra("result", Result(factor1 * factor2))
            }
            //startActivity(i)
            launcher.launch(i)

        }

        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            factor1 = Random.nextInt(from = 1, until = 13)
            factor2  = Random.nextInt(from = 1, until = 13)
            tvFactor1.text = factor1.toString()
            tvFactor2.text = factor2.toString()
        }




    }
}