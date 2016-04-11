package example.divyasingh.logtime;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.divyasingh.logtime.Interface.TimesheetsService;
import example.divyasingh.logtime.Models.FragmentTab1;
import example.divyasingh.logtime.Models.FragmentTab2;
import example.divyasingh.logtime.Models.FragmentTab3;
import example.divyasingh.logtime.Models.FragmentTab4;
import example.divyasingh.logtime.Models.RetrofitAdapter;
import example.divyasingh.logtime.Models.Timesheet;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TimesheetActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> oslist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        String authenticationToken = intent.getStringExtra("authenticationToken");
        String email = intent.getStringExtra("email");



        if(email!= null && authenticationToken!= null) {
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("authenticationToken", authenticationToken);
            FragmentTab4 fragInfo = new FragmentTab4();
            fragInfo.setArguments(bundle);
        }

        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Home", null),
                FragmentTab1.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Clients", null),
                FragmentTab2.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Projects", null),
                FragmentTab3.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Timesheets", null),
                FragmentTab4.class, null);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Action_settings selected", Toast.LENGTH_SHORT)
                        .show();
                return true;

            case R.id.signout:
                Toast.makeText(this, "Sign out selected", Toast.LENGTH_SHORT)
                        .show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


