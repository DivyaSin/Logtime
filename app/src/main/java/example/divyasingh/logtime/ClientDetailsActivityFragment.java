package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.ClientDetailsResponse;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientDetailsActivityFragment extends Fragment {

    @Bind(R.id.client_details)
    TextView tv;

    Call<ClientDetailsResponse> mResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_details, container, false);
        ButterKnife.bind(this, view);
        String id = getArguments().getString("id");
        String name = getArguments().getString("name");
        getClientDetails(id);
        return view;
    }

    private void getClientDetails(String id) {
        mResponse = ApiProvider.getService().fetchClientDetails(Integer.parseInt(id), LocalStorage.getInstance().getEmail(), LocalStorage.getInstance().getAuthToken());
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
        mResponse.enqueue(new Callback<ClientDetailsResponse>() {
            @Override
            public void onResponse(Call<ClientDetailsResponse> call, Response<ClientDetailsResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    tv.setText(response.body().getName());
                } else {
                    Log.v("ClientDetails", response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<ClientDetailsResponse> call, Throwable t) {
                Log.v("ClientDetails", "Failure");
            }
        });
    }
}
