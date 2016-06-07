package example.divyasingh.logtime;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateTimesheetFragment extends BaseFragment {

    @Bind(R.id.date_view)
    TextView dateView;

    DatePicker datePicker;
    Calendar calendar;

    @Bind(R.id.project_items)
    AppCompatSpinner mSpinnerItems;

    @Bind(R.id.hours_view)
    EditText hoursView;

    int year, month, day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_timesheet, container, false);
        ButterKnife.bind(this, view);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month+1, day);

        dateView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd MMMM yyyy"; // your format
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        dateView.setText(sdf.format(myCalendar.getTime()));
                    }
                };
                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }

//    public void setDate(View view) {
//        showDialog(999);
//        Toast.makeText(getActivity(), "ca", Toast.LENGTH_SHORT)
//                .show();
//    }
//
//
//    protected DatePickerDialog onCreateDialog(int id) {
//        // TODO Auto-generated method stub
//        if (id == 999) {
//            return new DatePickerDialog(getActivity(), myDateListener, year, month, day);
//        }
//        return null;
//    }
//
//    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
//            // TODO Auto-generated method stub
//            // arg1 = year
//            // arg2 = month
//            // arg3 = day
//            showDate(arg1, arg2+1, arg3);
//        }
//    };
//
//    private void showDate(int year, int month, int day) {
//        dateView.setText(new StringBuilder().append(day).append("/")
//                .append(month).append("/").append(year));
//    }


}
