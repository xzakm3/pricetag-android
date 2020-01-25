package com.example.pricetag.data.repositories.user;

import android.app.Activity;
import android.content.Intent;
import android.preference.Preference;
import android.widget.Toast;

import com.example.pricetag.activities.LoginActivity;
import com.example.pricetag.activities.MainActivity;
import com.example.pricetag.data.requests.user.LoginRequest;
import com.example.pricetag.data.requests.user.LoginResponse;
import com.example.pricetag.data.requests.user.SignupRequest;
import com.example.pricetag.services.RetrofitInstance;
import com.example.pricetag.services.UserService;
import com.example.pricetag.utils.Preferences;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public static final UserService service = RetrofitInstance.get().create(UserService.class);

    public static void signupUser(SignupRequest signupRequest, Activity context) {
        // Define our request and enqueue
        Call<Void> call = service.signup(signupRequest);
        

        // Go ahead and enqueue the request
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("message", "Sign up was successful");
                    context.startActivity(intent);
                } else {
                    Toasty.error(context, "Whoops, something went wrong!", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(context, t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

    public static void login(LoginRequest loginRequest, Activity context) {
        Call<LoginResponse> call = service.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(context, MainActivity.class);
                    Preferences.setAccessToken(context, response.body().getToken());
                    context.startActivity(intent);

                } else {
                    Toasty.error(context, "Whoops, something went wrong!", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toasty.error(context, t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }
}
