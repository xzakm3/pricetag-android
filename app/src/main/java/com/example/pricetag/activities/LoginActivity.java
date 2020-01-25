package com.example.pricetag.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricetag.R;
import com.example.pricetag.data.repositories.user.UserRepository;
import com.example.pricetag.data.requests.user.LoginRequest;
import com.example.pricetag.utils.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.signupButton)
    Button signupButton;

    @BindView(R.id.loginButton)
    Button loginButton;

    @BindView(R.id.username)
    EditText usernameEditText;

    @BindView(R.id.password)
    EditText passwordEditText;


    @Override
    protected void onResume() {
        super.onResume();

        String message = getIntent().getStringExtra("message");

        if (message != null && !message.equals("")) {
            Toasty.success(this, message, Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);


        loginButton.setOnClickListener(v -> {
            UserRepository.login(
                    new LoginRequest(usernameEditText.getText().toString(), passwordEditText.getText().toString()),
                    this);
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SignupActivity.class);
            startActivity(intent);
        });

        
        if (Preferences.getAccessToken(this) != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
