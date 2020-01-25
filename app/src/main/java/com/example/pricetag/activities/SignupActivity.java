package com.example.pricetag.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricetag.R;
import com.example.pricetag.data.requests.user.SignupRequest;
import com.example.pricetag.data.repositories.user.UserRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.signupButton)
    Button signupButton;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.confirmPassword)
    EditText passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        signupButton.setOnClickListener(this::handleSubmitForm);
    }

    protected void handleSubmitForm(View view) {
        SignupRequest user = new SignupRequest(username.getText().toString(), password.getText().toString(), passwordConfirm.getText().toString());
        if (user.validate()) {
            UserRepository.signupUser(user, this);
        } else {
            Toasty.error(this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
        }

    }


}
