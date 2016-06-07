package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.divyasingh.logtime.Models.ListTimesheetsResponse;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayTimesheetsFragment extends Fragment {

    @Bind(R.id.timesheets_list)
    ListView myListView;

    Call<List<ListTimesheetsResponse>> mResponse;

    List<ListTimesheetsResponse> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_timesheets, container, false);
        ButterKnife.bind(this, view);
        displayTimesheets();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Timesheets");
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTimesheetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayTimesheets() {
        mResponse = ApiProvider.getService().displayTimesheet();
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.fetching_timesheets));
        progressDialog.setCancelable(false);
        progressDialog.show();
        mResponse.enqueue(new Callback<List<ListTimesheetsResponse>>() {
            @Override
            public void onResponse(Call<List<ListTimesheetsResponse>> call, Response<List<ListTimesheetsResponse>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    List<ListTimesheetsResponse> timesheets = response.body();
                    for (ListTimesheetsResponse timesheet : timesheets) {
                        String project_id = timesheet.getProject_id();
                        String hours = timesheet.getHours();
                        String date = timesheet.getDate();
                        String standup = timesheet.getStandup_detail();
                        String user_id = timesheet.getUser_id();
                        mList.add(new ListTimesheetsResponse(project_id, hours, date, standup, user_id));
                    }
                    ListAdapter listAdapter = new TimesheetsAdapter(getContext(), R.layout.list_timesheets, mList);
                    myListView.setAdapter(listAdapter);
                } else {
                    Log.v("DisplayTimesheets", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<ListTimesheetsResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("displayTimesheetFailure", "failure");
            }
        });
    }

    public class TimesheetsAdapter extends ArrayAdapter<ListTimesheetsResponse> {

        private List<ListTimesheetsResponse> timesheets;

        public TimesheetsAdapter(Context context, int resource) {
            super(context, resource);
        }

        public TimesheetsAdapter(Context context, int resource, List<ListTimesheetsResponse> mList) {
            super(context, resource, mList);
            this.timesheets = mList;
        }

        @Override
        public int getCount() {
            return timesheets.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater;
                inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.list_timesheets, parent, false);
            }
            TextView projectView = (TextView) view.findViewById(R.id.project_id);
            TextView hoursView = (TextView) view.findViewById(R.id.hours);
            TextView dateView = (TextView) view.findViewById(R.id.date);
            TextView standUpView = (TextView) view.findViewById(R.id.standup);
            TextView userView = (TextView) view.findViewById(R.id.user);
            projectView.setText(timesheets.get(position).getProject_id());
            hoursView.setText(timesheets.get(position).getHours());
            dateView.setText(timesheets.get(position).getDate());
            standUpView.setText(timesheets.get(position).getStandup_detail());
            userView.setText(timesheets.get(position).getUser_id());
            return view;
        }
    }
}
