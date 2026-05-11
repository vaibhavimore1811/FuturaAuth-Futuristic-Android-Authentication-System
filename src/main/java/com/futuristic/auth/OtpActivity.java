package com.futuristic.auth;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.futuristic.auth.databinding.ActivityOtpBinding;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startResendTimer();

        binding.btnVerify.setOnClickListener(v -> {
            String otp = binding.etOtp.getText().toString();
            if (otp.length() == 4) {
                showSuccessState();
            } else {
                binding.tilOtp.setError("Invalid identity pulse");
            }
        });
    }

    private void startResendTimer() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                binding.tvTimer.setText("Resend pulse in " + millisUntilFinished / 1000 + "s");
            }
            public void onFinish() {
                binding.tvTimer.setText("Resend Pulse");
                binding.tvTimer.setTextColor(getColor(R.color.neon_blue));
            }
        }.start();
    }

    private void showSuccessState() {
        binding.successAnimation.setVisibility(View.VISIBLE);
        binding.successAnimation.playAnimation();
        binding.otpContainer.animate().alpha(0f).setDuration(500).start();
        
        binding.successAnimation.addAnimatorListener(new android.animation.Animator.AnimatorListener() {
            @Override public void onAnimationStart(android.animation.Animator animation) {}
            @Override public void onAnimationEnd(android.animation.Animator animation) {
                Toast.makeText(OtpActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override public void onAnimationCancel(android.animation.Animator animation) {}
            @Override public void onAnimationRepeat(android.animation.Animator animation) {}
        });
    }
}
