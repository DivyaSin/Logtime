package example.divyasingh.logtime;


import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
