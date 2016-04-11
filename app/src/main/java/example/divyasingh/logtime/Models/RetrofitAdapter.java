package example.divyasingh.logtime.Models;

import example.divyasingh.logtime.LoginActivity;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RetrofitAdapter {

    static RestAdapter restAdapter;

    static {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
            }
        };
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(LoginActivity.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

    private RetrofitAdapter() {

    }

    public static RestAdapter getRestAdapter() {
        return restAdapter;
    }

}

