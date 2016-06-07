package example.divyasingh.logtime;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import example.divyasingh.logtime.Models.FetchProjectsResponse;
import example.divyasingh.logtime.api.ApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchProjectsFragment extends Fragment {

    @Bind(R.id.projects_list)
    ListView myListView;

    Call<List<FetchProjectsResponse>> mResponse;

    List<FetchProjectsResponse> mList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fetch_projects, container, false);
        ButterKnife.bind(this, view);
        getProjects();
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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Projects");
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(),"Create a new Project " ,Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void getProjects() {
        mResponse = ApiProvider.getService().fetchProjects(LocalStorage.getInstance().getEmail(), LocalStorage.getInstance().getAuthToken());
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.fetching_projects));
        progressDialog.setCancelable(false);
        progressDialog.show();
        mResponse.enqueue(new Callback<List<FetchProjectsResponse>>() {
            @Override
            public void onResponse(Call<List<FetchProjectsResponse>> call, Response<List<FetchProjectsResponse>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    List<FetchProjectsResponse> projects = response.body();
                    for (FetchProjectsResponse project : projects) {
                        String name = project.getName();
                        String client_id = project.getClient_id();
                        String project_detail = project.getProject_detail();
                        String is_active = project.getActive();
                        mList.add(new FetchProjectsResponse(name, client_id, project_detail, is_active));
                    }
                    ListAdapter listAdapter = new ProjectAdapter(getContext(), R.layout.list_projects, mList);
                    myListView.setAdapter(listAdapter);
                } else {
                    Log.v("FetchProjects", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<FetchProjectsResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("FetchProjects", "Failure");
            }
        });
    }

    public class ProjectAdapter extends ArrayAdapter<FetchProjectsResponse> {

        private List<FetchProjectsResponse> projects;

        public ProjectAdapter(Context context, int resource) {
            super(context, resource);
        }

        public ProjectAdapter(Context context, int resource, List<FetchProjectsResponse> mList) {
            super(context, resource, mList);
            this.projects = mList;
        }

        @Override
        public int getCount() {
            return projects.size();
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
                view = inflater.inflate(R.layout.list_projects, parent, false);

            }
            TextView nameView = (TextView) view.findViewById(R.id.name);
            TextView clientView = (TextView) view.findViewById(R.id.client);
            TextView projectDetailView = (TextView) view.findViewById(R.id.project_detail);
            TextView isActiveView = (TextView) view.findViewById(R.id.is_active);
            nameView.setText(projects.get(position).getName());
            clientView.setText(projects.get(position).getClient_id());
            projectDetailView.setText(projects.get(position).getProject_detail());
            isActiveView.setText(projects.get(position).getActive());
            return view;
        }
    }
}
