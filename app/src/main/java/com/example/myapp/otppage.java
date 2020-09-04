package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otppage extends AppCompatActivity {
EditText Otp;
    String vCodeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppage);

        Otp = findViewById(R.id.otp);

        //SharedPreferences sharedPreferences =getSharedPreferences("settings",MODE_PRIVATE);
       // String phone= sharedPreferences.getString("PhoneNo","");

String phone=getIntent().getStringExtra("phone");
        sendVerificationCodeToUser(phone);

    }

public void verifyotp(View view){
        String code= Otp.getText().toString();
                if(code.isEmpty()){
                    Otp.setError("Otp needed to continue");
                    Otp.requestFocus();
                }

else if (code.length()<6){
    Otp.setError("wrong otp");

                }
verifyotp(code);

    }

private void sendVerificationCodeToUser(String phone){
    PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" +  phone,        // Phone number to verify
            60,                 // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
            mCallbacks);        // OnVerificationStateChangedCallbacks

}

private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    @Override

    public void onCodeSent(@NonNull String s,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
        super.onCodeSent(s,forceResendingToken);
        vCodeBySystem=s;
    }
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
    String code =phoneAuthCredential.getSmsCode();
    if (code!=null){
    verifyotp(code);

    }


    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        Toast.makeText(otppage.this,e.getMessage(),Toast.LENGTH_SHORT).show();

    }
};

    private void verifyotp (String codebyuser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vCodeBySystem, codebyuser);
        signinUserByCredential(credential);
    }

private void signinUserByCredential(PhoneAuthCredential credential){

    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();

    firebaseAuth.signInWithCredential(credential).addOnCompleteListener(otppage.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if (task.isSuccessful()) {
                Intent intent = new Intent(getApplicationContext(), Mainmenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(otppage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }

        }


    });
}

}