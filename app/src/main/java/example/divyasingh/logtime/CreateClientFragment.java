package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.NewClientRequest;
import example.divyasingh.logtime.Models.NewClientResponse;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateClientFragment extends BaseFragment {

    @Bind(R.id.create_client_button)
    Button cc_button;

    @Bind(R.id.client_name)
    EditText clientInput;

    Call<NewClientResponse> mResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_client, container, false);
        ButterKnife.bind(this, view);
        createClient();
        return view;
    }

    private void createClient() {

        cc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clientInput.getText().length() == 0)
                    Toast.makeText(getContext(), "Fill the missing field", Toast.LENGTH_SHORT).show();
                else {
                    NewClientRequest.Client client = new NewClientRequest.Client();
                    client.setName(clientInput.getText().toString());

                    NewClientRequest newClientRequest = new NewClientRequest();
                    newClientRequest.setClient(client);

                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage(getString(R.string.creatingClient));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    mResponse = ApiProvider.getService().createClient(LocalStorage.getInstance().getEmail(), LocalStorage.getInstance().getAuthToken(), "application/json", newClientRequest);
                    mResponse.enqueue(new Callback<NewClientResponse>() {
                        @Override
                        public void onResponse(Call<NewClientResponse> call, Response<NewClientResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent i = new Intent(getActivity(), ClientCreatedActivity.class);
                                i.putExtra("id", response.body().getId());
                                i.putExtra("name", response.body().getName());
                                startActivity(i);
                            } else {
                                Log.v("CreateClientResponse", response.errorBody().toString());
                            }
                        }
                        @Override
                        public void onFailure(Call<NewClientResponse> call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }
}
