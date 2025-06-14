package ma.enset.app_calcule_imc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var poidsInput: EditText? = null
    var tailleInput: EditText? = null
    var calculerBtn: Button? = null
    var resultatIMC: TextView? = null
    var categorieText: android.widget.TextView? = null
    var imageCategorie: ImageView? = null
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        poidsInput = findViewById<EditText>(R.id.poidsInput)
        tailleInput = findViewById<EditText>(R.id.tailleInput)
        calculerBtn = findViewById<Button>(R.id.calculerBtn)
        resultatIMC = findViewById<TextView>(R.id.resultatIMC)
        categorieText = findViewById<TextView>(R.id.categorieText)
        imageCategorie = findViewById<ImageView>(R.id.imageCategorie)

        calculerBtn?.setOnClickListener { calculerIMC() }
    }
    private fun calculerIMC() {
        val poidsStr = poidsInput!!.text.toString()
        val tailleStr = tailleInput!!.text.toString()

        if (poidsStr.isEmpty() || tailleStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        val poids = poidsStr.toFloat()
        val tailleCm = tailleStr.toFloat()
        val tailleM = tailleCm / 100

        val imc = poids / (tailleM * tailleM)

        val df = DecimalFormat("#.##")
        resultatIMC!!.text = "Votre IMC est: " + df.format(imc.toDouble())

        if (imc < 18.5) {
            categorieText!!.text = "Maigreur"
            imageCategorie!!.setImageResource(R.drawable.maigre)
        } else if (imc < 25) {
            categorieText!!.text = "Normal"
            imageCategorie!!.setImageResource(R.drawable.normal)
        } else if (imc < 30) {
            categorieText!!.text = "Sur Poids"
            imageCategorie!!.setImageResource(R.drawable.surpoids)
        } else {
            categorieText!!.text = "Obésité"
            imageCategorie!!.setImageResource(R.drawable.tobese)
        }
    }
}