package com.futuristic.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.futuristic.auth.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnRegister.setOnClickListener(v -> {
            if (validateFields()) {
                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private boolean validateFields() {
        String name = binding.etName.getText().toString();
        String email = binding.etEmail.getText().toString();
        
        if (TextUtils.isEmpty(name)) {
            binding.tilName.setError("Name is required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError("Email is required");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
