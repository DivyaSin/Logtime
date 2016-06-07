package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.FetchClientsResponse;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchClientsFragment extends Fragment {

    @Bind(R.id.clients_list)
    ListView myListView;

    List<String> names = new ArrayList<>();

    Call<List<FetchClientsResponse>> mResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fetch_clients, container, false);
        ButterKnife.bind(this, view);
        getClients();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Clients");
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.fab.show();
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateClientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getClients() {
        mResponse = ApiProvider.getService().fetchClients(LocalStorage.getInstance().getEmail(), LocalStorage.getInstance().getAuthToken());
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.fetching_clients));
        progressDialog.setCancelable(false);
        progressDialog.show();
        mResponse.enqueue(new Callback<List<FetchClientsResponse>>() {
            @Override
            public void onResponse(Call<List<FetchClientsResponse>> call, Response<List<FetchClientsResponse>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    final List<FetchClientsResponse> clients = response.body();
                    for (FetchClientsResponse client : clients) {
                        names.add(client.getName());
                    }
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
                    myListView.setAdapter(adapter);
                    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                            Intent i = new Intent(getActivity(), ClientDetailsActivity.class);
                            i.putExtra("id", clients.get(position).getId());
                            i.putExtra("name", clients.get(position).getName());
                            startActivity(i);
                        }
                    });
                } else {
                    Log.v("FetchClients", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<FetchClientsResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("FetchClients", "Failure");
            }
        });
    }
}