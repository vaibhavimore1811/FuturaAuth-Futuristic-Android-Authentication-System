package com.futuristic.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.futuristic.auth.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Cinematic Animations
        startCinematicAnimations();

        // Transition to Login after 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3500);
    }

    private void startCinematicAnimations() {
        // Logo Scale and Fade In
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(1500);
        binding.logoContainer.startAnimation(fadeIn);

        // Subtitle Slide Up
        binding.appName.setAlpha(0f);
        binding.appName.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(1000)
                .setStartDelay(1000)
                .start();
        
        binding.tagline.setAlpha(0f);
        binding.tagline.animate()
                .alpha(1f)
                .setDuration(1000)
                .setStartDelay(1800)
                .start();
    }
}
