package com.futuristic.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.futuristic.auth.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply Entrance Animations
        applyEntranceAnimations();

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String pass = binding.etPassword.getText().toString();

            if (validateInputs(email, pass)) {
                // Success Interaction
                performLoginSuccess();
            }
        });

        binding.tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    private void applyEntranceAnimations() {
        binding.loginCard.setTranslationY(400f);
        binding.loginCard.setAlpha(0f);
        binding.loginCard.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(800)
                .setStartDelay(200)
                .start();

        binding.headerText.setAlpha(0f);
        binding.headerText.animate()
                .alpha(1f)
                .setDuration(600)
                .setStartDelay(600)
                .start();
    }

    private boolean validateInputs(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError("Identity required");
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            binding.tilPassword.setError("Access key required");
            return false;
        }
        binding.tilEmail.setError(null);
        binding.tilPassword.setError(null);
        return true;
    }

    private void performLoginSuccess() {
        binding.btnLogin.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(() -> {
            binding.btnLogin.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            
            // Navigate to OTP
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }).start();
    }
}
