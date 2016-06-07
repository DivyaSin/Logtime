package example.divyasingh.logtime;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class UpdateClientActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_client);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);

        UpdateClientFragment updateClientFragment = new UpdateClientFragment();
        updateClientFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl, updateClientFragment).commit();

    }
}