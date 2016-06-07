package example.divyasingh.logtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.NewClientRequest;
import retrofit2.Call;

public class ClientCreatedFragment extends BaseFragment {

    @Bind(R.id.clientName)
    TextView clientView;

    @Bind(R.id.editClient)
    TextView editView;

    Call<NewClientRequest> mResponse;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_created_client, container, false);
        ButterKnife.bind(this, view);
        String id = getArguments().getString("id");
        String name = getArguments().getString("name");
        clientView.setText(name);
        editClient(id, name);
        return view;
    }

    private void editClient(final String id, final String name) {

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), UpdateClientActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
//
            }
        });
    }
}
