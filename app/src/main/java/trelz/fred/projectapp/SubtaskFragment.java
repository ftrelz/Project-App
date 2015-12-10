package trelz.fred.projectapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import java.util.*;

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
public class SubtaskFragment extends Fragment {

    /*private static final String ARG_TASK_ID = "task_id";
    private Project mSubTask;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mTimeButton;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private void addSubtasktoList(Project Object)
    {
        ListLab.get(getActivity()).addObjecttoList(Object);
    }


    public static ProjectFragment newInstance(UUID taskId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Project p = new Project();
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);

        if (ListLab.get(getActivity()).getProjectListSize() == 0) {
            mSubTask = p;
        } else {
            for (int i = 0; i < ListLab.get(getActivity()).getProjectListSize(); i++) {
                p = (Project) ListLab.get(getActivity()).getObject(i);
                if (p.getUUID() == taskId) {
                    mSubTask = p;
                    return;
                }
            }
        }
        //mTask = ListLab.get(getActivity()).getTask(taskId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);

        mTitleField = (EditText) v.findViewById(R.id.management_title);
        if (mSubTask.getName() == "") {
            mTitleField.setText("New Project");
        } else {
            mTitleField.setText(mSubTask.getName());
        }
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubTask.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.management_description);
        mDescriptionField.setText(mSubTask.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*mDateButton = (Button) v.findViewById(R.id.project_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateFragment dialog = DateFragment
                        .newInstance(mSubTask.getDeadlineDate());
                dialog.setTargetFragment(ProjectFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton = (Button) v.findViewById(R.id.project_time);
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimeFragment dialog = TimeFragment
                        .newInstance(mSubTask.getDeadlineTime());
                dialog.setTargetFragment(ProjectFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });

        //mNextButton = (Button) v.findViewById(R.id.project_time);


        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DateFragment.USER_DATE);
            date.setHours(mSubTask.getHour());
            date.setMinutes(mSubTask.getMinute());
            mSubTask.setDeadlineDate(date);
            mSubTask.setDeadlineTime(date);
            updateDate();
            updateTime();
        }

        if (requestCode == REQUEST_TIME) {
            Date time = (Date) data
                    .getSerializableExtra(TimeFragment.USER_TIME);
            time.setYear(mSubTask.getYear());
            time.setMonth(mSubTask.getMonth());
            time.setDate(mSubTask.getDay());
            mSubTask.setDeadlineTime(time);
            mSubTask.setDeadlineDate(time);
            updateTime();
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mSubTask.getDeadlineDate().toString());
    }

    private void updateTime() {
        mTimeButton.setText(mSubTask.getDeadlineTime().toString());*/
   // }

}