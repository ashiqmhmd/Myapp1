package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginpage extends AppCompatActivity {

EditText PhoneNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        PhoneNO =findViewById(R.id.editTextPhone);
    }
    public void generate(View view){

    String phone=PhoneNO.getText().toString();
       // SharedPreferences sharedPreferences=getSharedPreferences("access",MODE_PRIVATE);
       // SharedPreferences.Editor editor = sharedPreferences.edit();
       // editor.putString("PhoneNo",phone);
      //  editor.apply();

        if (phone.isEmpty()){
            PhoneNO.setError("please enter your no");
            PhoneNO.requestFocus();

        }
    else if (phone.length()!= 10){
            PhoneNO.setError("please enter verified no");
            PhoneNO.requestFocus();

        }
    else{
            Intent intent = new Intent(getApplicationContext(), otppage.class);
            intent.putExtra("phone",phone);
            startActivity(intent);
        }

    }


}