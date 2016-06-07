package example.divyasingh.logtime.api;

import android.text.TextUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;

import example.divyasingh.logtime.BuildConfig;
import example.divyasingh.logtime.LocalStorage;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static final String BASE_URL = "https://try-mblt.herokuapp.com";
    private static Retrofit mRetrofit;

    @Getter
    @Setter
    private static LogTimeService service;

    static {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String token = LocalStorage.getInstance().getAuthToken();
                String email = LocalStorage.getInstance().getEmail();
                String name = LocalStorage.getInstance().getName();
                request.newBuilder().url(request.url().newBuilder().build()).build();

                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(interceptor);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = mRetrofit.create(LogTimeService.class);
    }
    public static Retrofit getRetrofitInstance() {
        return  mRetrofit;
    }
}
