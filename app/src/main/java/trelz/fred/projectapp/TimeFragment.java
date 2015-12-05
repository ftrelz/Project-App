package trelz.fred.projectapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jc_cisneros21 on 12/4/15.
 */
public class TimeFragment extends DialogFragment{

    public static final String USER_TIME = "trelz.fred.projectapp.time";
    private static final String ARG_TIME = "time";

    private TimePicker mTime;

    public static TimeFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimeFragment fragment = new TimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTime = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTime.setHour(hour);
            mTime.setMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                int minute = 0;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    hour = mTime.getHour();
                                    minute = mTime.getMinute();
                                }
                                int year = calendar.get(Calendar.YEAR);
                                int month = calendar.get(Calendar.MONTH);
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();

    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(USER_TIME, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
