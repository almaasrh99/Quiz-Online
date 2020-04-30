package com.almaas.quizonline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    Button btn_kirimData;
    EditText et_nama,et_nis;
    String nama,nis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btn_kirimData = (Button) findViewById(R.id.btn_mulai);
        et_nama       = (EditText) findViewById(R.id.tv_username);
        et_nis       = (EditText) findViewById(R.id.tv_nis);

        btn_kirimData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = et_nama.getText().toString();
                nis = et_nis.getText().toString();

                Intent i = new Intent(FormActivity.this,DashboardActivity.class);
                Intent a = new Intent(FormActivity.this,ScoreActivity.class);

                if(nama.equals("") || nis.equals("")){
                    Toast.makeText(FormActivity.this, "Nama atau NIS tidak boleh kosong !",
                            Toast.LENGTH_LONG).show();
                }else{
                    i.putExtra("nama",nama);
                    i.putExtra("nis",nis);
                    Toast.makeText(FormActivity.this, "Enjoy Your Quiz!",
                            Toast.LENGTH_LONG).show();
                    startActivity(i);

                }

            }
        });
    }
}
