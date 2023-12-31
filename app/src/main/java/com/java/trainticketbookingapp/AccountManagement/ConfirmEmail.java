package com.java.trainticketbookingapp.AccountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.java.trainticketbookingapp.AccountManagement.LoginActivity;
import com.java.trainticketbookingapp.R;

public class ConfirmEmail extends AppCompatActivity {
    static final int CODE = 100;
    private EditText etEmail;
    private Button btnNext, btnBack;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);

        etEmail = findViewById(R.id.et_email_cf);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmEmail.this, LoginActivity.class);
            startActivity(intent);
        });

        btnNext.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            checkEmail(email);
        });


    }

    private void checkEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(ConfirmEmail.this, getString(R.string.invalid_format_email), Toast.LENGTH_SHORT).show();
            return;
        }

        auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                    if (!isNewUser) {
                        // The email exists
                        auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ConfirmEmail.this, getString(R.string.verify_cf_email), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ConfirmEmail.this, LoginActivity.class);
                                            intent.putExtra("email", email);
                                            startActivityForResult(intent, CODE);
                                        } else {
                                            Toast.makeText(ConfirmEmail.this, getString(R.string.fail_cf_email), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(ConfirmEmail.this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CODE) {
            etEmail.setText(data.getStringExtra("email"));
        }
    }
}
