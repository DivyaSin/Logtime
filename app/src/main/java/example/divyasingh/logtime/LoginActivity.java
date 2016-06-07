package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.LoginResponse;
import example.divyasingh.logtime.Models.UserRequest;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static example.divyasingh.logtime.Models.UserRequest.User;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.email_id)
    EditText emailInput;

    @Bind(R.id.password)
    EditText passwordInput;

    @Bind(R.id.button)
    Button signInButton;

    Call<LoginResponse> mResponse;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (LocalStorage.getInstance().isLoggedIn()) {
            // redirect to home screen
            Intent home_intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(home_intent);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);

            signInButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (emailInput.getText().length() == 0 || passwordInput.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Fill the missing field", Toast.LENGTH_SHORT).show();
                    } else {
                        email = emailInput.getText().toString();
                        password = passwordInput.getText().toString();

                        User user = new User();
                        user.setEmail(email);
                        user.setPassword(password);

                        UserRequest userRequest = new UserRequest();
                        userRequest.setUser(user);

                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage(getString(R.string.Loading));
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        mResponse = ApiProvider.getService().login("application/json", userRequest);
                        mResponse.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.isSuccessful()) {
                                    LocalStorage.getInstance().setLoggedIn(true);
                                    LocalStorage.getInstance().setEmail(response.body().getUser().getEmail());
                                    LocalStorage.getInstance().setName(response.body().getUser().getName());
                                    LocalStorage.getInstance().setAuthToken(response.body().getAuthentication_token());
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    progressDialog.dismiss();
                                    startActivity(i);
                                } else {
                                    Log.v("LoginResponse", response.errorBody().toString());
                                }
                            }
                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }
}


