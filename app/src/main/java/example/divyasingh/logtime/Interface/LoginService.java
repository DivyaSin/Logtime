package example.divyasingh.logtime.Interface;

import example.divyasingh.logtime.Models.LoginResponse;
import example.divyasingh.logtime.Models.UserRequest;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginService {

    @POST("/users/sign_in.json")

    void login(@Body UserRequest userRequest, Callback<LoginResponse> response);


}