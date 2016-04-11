package example.divyasingh.logtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import example.divyasingh.logtime.Models.LoginResponse;
import example.divyasingh.logtime.Interface.LoginService;
import example.divyasingh.logtime.Models.RetrofitAdapter;
import example.divyasingh.logtime.Models.UserRequest;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://try-mblt.herokuapp.com";

    String email, password;
    EditText Email, Password;
    Button signInButton;
    ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.email_id);
        Password = (EditText) findViewById(R.id.password);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setVisibility(View.INVISIBLE);

        signInButton = (Button) findViewById(R.id.button);
        signInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (Email.getText().length() == 0 || Password.getText().length() == 0) {

                    Toast.makeText(getApplicationContext(), "Fill the missing field", Toast.LENGTH_SHORT).show();

                } else {
                    email = Email.getText().toString();
                    password = Password.getText().toString();

                    RestAdapter restAdapter = RetrofitAdapter.getRestAdapter();
                    LoginService mLoginService = restAdapter.create(LoginService.class);

                    UserRequest userRequest = new UserRequest();

                    UserRequest.User user = new UserRequest.User();
                    user.setEmail(email);
                    user.setPassword(password);
                    userRequest.setUser(user);
                    mProgress.setVisibility(View.VISIBLE);

                    mLoginService.login(userRequest, new Callback<LoginResponse>() {
                        @Override
                        public void success(LoginResponse loginResponse, Response response) {

                            Log.v("MainActivity", loginResponse.toString());
                            Intent i = new Intent(LoginActivity.this, TimesheetActivity.class);
                            i.putExtra("authenticationToken", loginResponse.getAuthentication_token());
                            i.putExtra("email", loginResponse.getUser().getEmail());
                            Log.v("MainActivity", loginResponse.getAuthentication_token());
                            mProgress.setVisibility(View.INVISIBLE);
                            startActivity(i);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            mProgress.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}

