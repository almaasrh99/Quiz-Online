package com.almaas.quizonline

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.almaas.quizonline.MainActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score.*
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.GoogleAuthProvider
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class ScoreActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient ///
    private lateinit var t1_total:TextView
    private lateinit var t2_correct:TextView
    private lateinit var t3_wrong:TextView
    private lateinit var t4_hasil:TextView
    private lateinit var total: String
    private lateinit var Nameuser: TextView
    private lateinit var correct:String
    private lateinit var incorrect:String
    private lateinit var nilai:String
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)


        auth = FirebaseAuth.getInstance()
        t2_correct = findViewById(R.id.correct) as TextView
        t3_wrong = findViewById<View>(R.id.incorrect) as TextView
        t1_total = findViewById<View>(R.id.attempted) as TextView
        t4_hasil = findViewById<View>(R.id.hasil) as TextView
        val i = intent
        total = i.getStringExtra("total")
        correct = i.getStringExtra("correct")
        incorrect = i.getStringExtra("incorrect")
        nilai = i.getStringExtra("hasil")

        setValues()
//        setupUI()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(this, gso)


//        if(getIntent().getStringExtra("hobi") != null){
//            hobi = getIntent().getStringExtra("hobi");
//            tv_hobi.setText(hobi);
//        }
    }


//    private fun setupUI() {
//        btn_out.setOnClickListener {
//            signOut()
////            val user = firebaseAuth.getCurrentUser()
////            if (user != null) {
////                val Nama= findViewById<View>(R.id.user) as TextView
////                Nama.text = user.displayName.toString()
////
////            }
//        }
//    }


//    private fun signOut() {
//        googleSignInClient.signOut()
//            .addOnCompleteListener(this, OnCompleteListener<Void> {
//                Toast.makeText(
//                baseContext, "Anda LogOut",
//                Toast.LENGTH_SHORT
//            ).show()
//                FirebaseAuth.getInstance().signOut()
//                startActivity(Intent(this, SplashActivity::class.java))
//                finish()
//            })
//    }

    private fun setValues() {
        t1_total.setText(total)
        t2_correct.setText(correct)
        t3_wrong.setText(incorrect)
        t4_hasil.setText(nilai)
    }

    fun backToQuiz(view: View) {
        startActivity(Intent(this, FormActivity::class.java))
        finish()

    }

    fun LogOut(view: View) {
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

