package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginpage extends AppCompatActivity {

EditText Phoneno;
CardView generateotpcardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        Phoneno =findViewById(R.id.editTextPhone);
    }
    public void generate(View view){
        generateotpcardview = findViewById(R.id.generateotpcardview);

    String phone=Phoneno.getText().toString();
        SharedPreferences sharedPreferences=getSharedPreferences("access",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Phoneno",phone);
        editor.apply();

        if (phone.isEmpty()){
            Phoneno.setError("please enter your no");
            Phoneno.requestFocus();

        }
    else if (Phoneno.length()!=10){
            Phoneno.setError("please enter verified no");
            Phoneno.requestFocus();

        }
    else{
            Intent intent = new Intent(getApplicationContext(), otppage.class);
            startActivity(intent);
        }

    }


}