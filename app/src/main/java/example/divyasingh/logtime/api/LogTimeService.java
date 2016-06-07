package example.divyasingh.logtime.api;

import java.util.List;

import example.divyasingh.logtime.Models.ClientDetailsResponse;
import example.divyasingh.logtime.Models.CreateTimesheetRequest;
import example.divyasingh.logtime.Models.FetchClientsResponse;
import example.divyasingh.logtime.Models.FetchProjectsResponse;
import example.divyasingh.logtime.Models.ListTimesheetsResponse;
import example.divyasingh.logtime.Models.LoginResponse;
import example.divyasingh.logtime.Models.NewClientRequest;
import example.divyasingh.logtime.Models.NewClientResponse;
import example.divyasingh.logtime.Models.UserRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LogTimeService {

    @POST("/users/sign_in.json")
    Call<LoginResponse> login(@Header("Content-Type") String contentType, @Body UserRequest userRequest);

    @GET("/timesheets.json")
    Call<List<ListTimesheetsResponse>> displayTimesheet();

    @POST("/timesheets.json")
    Call<CreateTimesheetRequest> createTimesheet();

    @PUT("/clients/{id}.json")
    Call<NewClientRequest> updateClient(@Path("id") int id, @Header("X-User-Email") String email, @Header("X-User-Token") String token, @Header("Content-Type") String contentType, @Body NewClientRequest newClientRequest );

    @POST("/clients.json")
    Call<NewClientResponse> createClient(@Header("X-User-Email") String email, @Header("X-User-Token") String token, @Header("Content-Type") String contentType, @Body NewClientRequest newClientRequest);

    @GET("/projects.json")
    Call<List<FetchProjectsResponse>> fetchProjects(@Header("X-User-Email") String email, @Header("X-User-Token") String token);

    @GET("/clients.json")
    Call<List<FetchClientsResponse>> fetchClients(@Header("X-User-Email") String email, @Header("X-User-Token") String token);

    @GET("/clients/{id}.json")
    Call<ClientDetailsResponse> fetchClientDetails(@Path("id") int id, @Header("X-User-Email") String email, @Header("X-User-Token") String token);
}
