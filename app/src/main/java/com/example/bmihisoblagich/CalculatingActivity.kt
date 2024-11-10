package uz.iskandarbek.bmihisoblagich

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener

class CalculatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculating)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        val category: TextView = findViewById(R.id.category)

        editTextHeight.addTextChangedListener {
           if (editTextHeight.text.isBlank()){
               textViewResult.text = ""
               category.text = ""
           }
        }

        editTextWeight.addTextChangedListener {
            if (editTextWeight.text.isBlank()){
                textViewResult.text = ""
                category.text = ""
            }
        }

        buttonCalculate.setOnClickListener {
            val weightStr = editTextWeight.text.toString()
            val heightStr = editTextHeight.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat()
                val bmi = calculateBMI(weight, height)
                val bmiCategory = getBMICategory(bmi)

                textViewResult.text = "Sizning BMI ko'rsatkichigiz: %.2f - %s".format(bmi , bmiCategory)
                category.text = bmiCategory

            } else {
                Toast.makeText(this, "Iltimos, barcha maydonlarni to'ldiring", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateBMI(weight: Float, height: Float): Float {
        return weight / (height * height)
    }

    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Ozg'in"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Ortacha semiz"
            bmi >= 30.0 -> "Semiz"
            else -> "Noaniq"
        }
    }
}