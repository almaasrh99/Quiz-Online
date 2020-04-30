package com.almaas.quizonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetDialog


class DashboardActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var nama: String
    private lateinit var nis: String
    private lateinit var Nameuser: TextView
    private lateinit var NIS: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        auth = FirebaseAuth.getInstance()

        Nameuser = findViewById(R.id.textView2) as TextView
        NIS = findViewById(R.id.Tvnis) as TextView

        if(getIntent().getStringExtra("nama") != null){
            nama = getIntent().getStringExtra("nama");
            textView2.setText(nama);
        }
        if(getIntent().getStringExtra("nis") != null){
            nis= getIntent().getStringExtra("nis");
           Tvnis.setText(nis);
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

//        btn_out.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//
//        }
    }

    fun math(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Question")
        startActivity(myIntent)
    }
    fun sejarah(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Sejarah")
        startActivity(myIntent)
    }
    fun fisika(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Fisika")
        startActivity(myIntent)
    }
    fun ekonomi(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Ekonomi")
        startActivity(myIntent)
    }
    fun sosiologi(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Sosiologi")
        startActivity(myIntent)
    }
    fun biologi(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Biologi")
        startActivity(myIntent)
    }
    fun kimia(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Kimia")
        startActivity(myIntent)
    }
    fun geo(view: View) {
        val myIntent = Intent(this, QuestionActivity::class.java)
        myIntent.putExtra("subject", "Geografi")
        startActivity(myIntent)
    }
    fun info(view: View) {
        showAbout()
    }
    private fun showAbout() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.activity_info, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun signOut(view: View) {
        googleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                Toast.makeText(
                    baseContext, "Anda LogOut",
                    Toast.LENGTH_SHORT
                ).show()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            })
    }
}
