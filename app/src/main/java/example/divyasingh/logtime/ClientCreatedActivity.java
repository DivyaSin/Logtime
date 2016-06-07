package example.divyasingh.logtime;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class ClientCreatedActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_client);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        getSupportActionBar().setTitle(name);

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);

        ClientCreatedFragment clientCreatedFragment = new ClientCreatedFragment();
        clientCreatedFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, clientCreatedFragment).commit();
    }
}
