package trelz.fred.projectapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import java.util.*;

import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by jc_cisneros21 on 11/22/15.
 */
public class TaskFragment extends Fragment{

    private static final String ARG_TASK_ID = "task_id";
    private Task mTask;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mDeleteBox;
    private boolean delete_on;
    private int task_index;
    private boolean new_task_check;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private void addTasktoList(Task task)
    {
        TaskLab.get(getActivity()).addTasktoList(task);
    }


    public static TaskFragment newInstance(UUID taskId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Task t = new Task();
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        View b = this.getActivity().findViewById(R.id.add_task);
        b.setVisibility(View.INVISIBLE);


        if (TaskLab.get(getActivity()).getTaskListSize() == 0) {
            mTask = t;
        } else {
            for (int i = 0; i < TaskLab.get(getActivity()).getTaskListSize(); i++) {
                t = TaskLab.get(getActivity()).getTask(i);
                if (taskId.compareTo(t.getUUID()) != 0) {
                    t = new Task();
                    mTask = t;
                }
                else
                {
                    task_index = i;
                    mTask = t;
                    return;
                }
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        // Set delet_on to false so we know
        // that we don't have to delete the project
        delete_on = false;

        // User inserts his title for Task
        mTitleField = (EditText) v.findViewById(R.id.task_title);
        if (mTask.getName() == "") {
            mTitleField.setText("New Task");
        } else {
            mTitleField.setText(mTask.getName());
        }
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

        // User picks Task's Date
        mDateButton = (Button) v.findViewById(R.id.task_date);
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

        // User picks Task's Time
        mTimeButton = (Button) v.findViewById(R.id.task_time);
        updateTime();
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

        // Deletes the Task
        mDeleteBox = (CheckBox) v.findViewById(R.id.delete_task);
        mDeleteBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteBox.isChecked() == true) {
                    delete_on = true;
                    if (mTask.getBool() == false) {
                        new_task_check = true;
                    } else {
                        TaskLab.get(getActivity()).deleteTask(task_index);
                    }
                }
                else {
                    delete_on = false;
                    if (new_task_check == true) {
                        new_task_check = false;
                    }
                    else {
                        TaskLab.get(getActivity()).addTaskIndex(task_index,mTask);
                    }
                }
            }
        });

        // Adds the Task to the Arraylist if the
        // conditions are met.
        if (delete_on == false) {
            if (mTask.getBool() == false) {
                mTask.setBool(true);
                addTasktoList(mTask);
            }
        }

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        // User sets the date
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DateFragment.USER_DATE);
            date.setHours(mTask.getHour());
            date.setMinutes(mTask.getMinute());
            mTask.setDeadlineDate(date);
            mTask.setDeadlineTime(date);
            updateDate();
            updateTime();
        }

        // User sets the time
        if (requestCode == REQUEST_TIME) {
            Date time = (Date) data
                    .getSerializableExtra(TimeFragment.USER_TIME);
            time.setYear(mTask.getYear());
            time.setMonth(mTask.getMonth());
            time.setDate(mTask.getDay());
            mTask.setDeadlineTime(time);
            mTask.setDeadlineDate(time);
            updateTime();
            updateDate();
        }
    }

    // Updating Date
    private void updateDate() {
        mDateButton.setText(mTask.getDeadlineDate().toString());
    }

    // Updating Time
    private void updateTime() {
        mTimeButton.setText(mTask.getDeadlineTime().toString());
    }

}