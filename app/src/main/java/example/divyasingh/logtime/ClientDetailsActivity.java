package example.divyasingh.logtime;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class ClientDetailsActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        getSupportActionBar().setTitle(name);

        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        ClientDetailsActivityFragment clientDetailsActivityFragment = new ClientDetailsActivityFragment();
        clientDetailsActivityFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.your_placeholder, clientDetailsActivityFragment).commit();

    }
}