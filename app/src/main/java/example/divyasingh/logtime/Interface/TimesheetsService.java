package example.divyasingh.logtime.Interface;

import java.util.ArrayList;
import java.util.List;

import example.divyasingh.logtime.Models.Timesheet;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface TimesheetsService {

    @GET("/timesheets.json")

    void displayTimesheet(@Header("X-User-Token") String authenticationToken,
                          @Header("X-User-Email") String email,
                          Callback<ArrayList<Timesheet>> timesheet );
}
