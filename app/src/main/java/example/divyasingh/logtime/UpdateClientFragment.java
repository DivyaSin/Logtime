package example.divyasingh.logtime;

import android.app.ProgressDialog;
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
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateClientFragment extends BaseFragment {

    @Bind(R.id.client_name)
    EditText clientInput;

    @Bind(R.id.update_client_button)
    Button uc_button;

    Call<NewClientRequest> mResponse;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updated_client, container, false);
        ButterKnife.bind(this, view);
        String id = getArguments().getString("id");
        String name = getArguments().getString("name");
        clientInput.setText(name);
        updateClient(id);
        return view;
    }

    private void updateClient(final String id) {

        uc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clientInput.getText().length() == 0)
                    Toast.makeText(getContext(), "Fill the missing field", Toast.LENGTH_SHORT).show();
                else {

                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage(getString(R.string.creatingClient));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    NewClientRequest.Client client = new NewClientRequest.Client();
                    client.setName(clientInput.getText().toString());

                    NewClientRequest newClientRequest = new NewClientRequest();
                    newClientRequest.setClient(client);

                    mResponse = ApiProvider.getService().updateClient(Integer.parseInt(id), LocalStorage.getInstance().getEmail(), LocalStorage.getInstance().getAuthToken(), "application/json", newClientRequest);
                    mResponse.enqueue(new Callback<NewClientRequest>() {
                        @Override
                        public void onResponse(Call<NewClientRequest> call, Response<NewClientRequest> response) {
                            if (response.isSuccessful())
                                Log.v("UpdateClient", "Success");
                        }

                        @Override
                        public void onFailure(Call<NewClientRequest> call, Throwable t) {
                            Log.v("UpdateClient", "Failure");
                        }

                    });
                }
            }
        });
    }
}