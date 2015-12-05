package trelz.fred.projectapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import java.sql.Time;
import java.util.*;

import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jc_cisneros21 on 11/22/15.
 */
public class TaskFragment extends Fragment{

    private static final String ARG_TASK_ID = "task_id";
    private Task mTask;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mTimeButton;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";


    public static TaskFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, crimeId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        System.out.println("Printing taskId: " + taskId);
        mTask = TaskLab.get(getActivity()).getTask(taskId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_management, container, false);

        mTitleField = (EditText) v.findViewById(R.id.management_title);
        mTitleField.setText(mTask.getName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.management_description);
        mDescriptionField.setText(mTask.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.project_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateFragment dialog = DateFragment
                        .newInstance(mTask.getDeadlineDate());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton = (Button) v.findViewById(R.id.project_time);
        updateDate();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimeFragment dialog = TimeFragment
                        .newInstance(mTask.getDeadlineTime());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DateFragment.USER_DATE);
            mTask.setDeadlineDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mTask.getDeadlineDate().toString());
    }

}
