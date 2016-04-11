package example.divyasingh.logtime.Models;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import example.divyasingh.logtime.Interface.TimesheetsService;
import example.divyasingh.logtime.R;
import example.divyasingh.logtime.TimesheetActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentTab4 extends Fragment{

    ArrayList<HashMap<String, String>> oslist;
    ListView list;
    TextView tv1, tv2, tv3, tv4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oslist = new ArrayList<>();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        String email = ((TimesheetActivity) getActivity()).getEmail();
        Log.v("ft4", "jdfidljf");
//

        String email = getArguments().getString("email");
        String authenticationToken = getArguments().getString("authenticationToken");
//        String authenticationToken =  ((TimesheetActivity) getActivity()).getAuthenticationToken();

        RestAdapter restAdapter = RetrofitAdapter.getRestAdapter();
        TimesheetsService mTimesheetsService = restAdapter.create(TimesheetsService.class);
        mTimesheetsService.displayTimesheet(authenticationToken, email, new Callback<ArrayList<Timesheet>>() {
            @Override
            public void success(ArrayList<Timesheet> timesheets, Response response) {
                    int length = timesheets.size();
              for ( int i = 0; i < length; i++) {

                  String project_id = timesheets.get(i).getProjectId();
                  String hours = timesheets.get(i).getHours();
                  String standup_detail = timesheets.get(i).getStandupDetail();
                  String date = timesheets.get(i).getDate();

                  HashMap<String, String> map = new HashMap<>();

                  map.put(project_id, project_id);
                  map.put(hours, hours);
                  map.put(standup_detail, standup_detail);
                  map.put(date, date);

                    oslist.add(map);

                  ListAdapter adapter = new SimpleAdapter(getContext(), oslist,
                          R.layout.column_row,
                          new String[] { project_id, hours, standup_detail, date }, new int[] {
                          R.id.project_id, R.id.hours, R.id.standup_detail, R.id.date});
                  list.setAdapter(adapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TimesheetActivity", error.getLocalizedMessage());
            }

        });



        View v = inflater.inflate(R.layout.fragments_layout, container, false);
        TextView tv = (TextView) v.findViewById(R.id.text);
        tv.setText("Listing Timesheets");
        ListView lv = (ListView) v.findViewById(R.id.list);
        tv1 =(TextView) v.findViewById(R.id.project_id);
        tv2 =(TextView) v.findViewById(R.id.hours);
        tv3 =(TextView) v.findViewById(R.id.standup_detail);
        tv4 =(TextView) v.findViewById(R.id.date);
        return v;




    }

}
