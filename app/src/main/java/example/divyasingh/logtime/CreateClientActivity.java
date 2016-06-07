package example.divyasingh.logtime;

import android.os.Bundle;

public class CreateClientActivity extends BaseToolBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);
        getSupportActionBar().setTitle(getString(R.string.create_client));
    }
}
