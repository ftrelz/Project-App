package trelz.fred.projectapp;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import java.io.Serializable;
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
public class ProjectFragment extends Fragment {

    private static final String ARG_PROJECT_ID = "project_id";
    private Project mProject;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mTimeButton;
    private Button mNextButton;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private void addProjecttoList(Project object)
    {
        ListLab.get(getActivity()).addObjecttoList(object);
    }


    public static ProjectFragment newInstance(UUID projectId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectId);

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Project p = new Project();
        UUID projectId = (UUID) getArguments().getSerializable(ARG_PROJECT_ID);
        System.out.println(projectId);
        View b = this.getActivity().findViewById(R.id.add_project);
        b.setVisibility(View.INVISIBLE);


        if (ListLab.get(getActivity()).getProjectListSize() == 0) {
            mProject = p;
        } else {
            for (int i = 0; i < ListLab.get(getActivity()).getProjectListSize(); i++) {
                System.out.println("This is printing in oncreate!");
                p = ListLab.get(getActivity()).getProject(i);
                System.out.println(p.getUUID());
                if (projectId.compareTo(p.getUUID()) != 0) {
                    p = new Project();
                    mProject = p;
                }
                else
                {
                    mProject = p;
                    return;
                }
            }
        }
        //mTask = ListLab.get(getActivity()).getTask(taskId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);

        mTitleField = (EditText) v.findViewById(R.id.project_title);
        if (mProject.getName() == "") {
            mTitleField.setText("New Project");
        } else {
            mTitleField.setText(mProject.getName());
        }
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.project_description);
        mDescriptionField.setText(mProject.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setDescription(s.toString());
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
                        .newInstance(mProject.getDeadlineDate());
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
                        .newInstance(mProject.getDeadlineTime());
                dialog.setTargetFragment(ProjectFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });

        mNextButton = (Button) v.findViewById(R.id.poject_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mProject.getBool() == false) {
                    mProject.setBool(true);
                    addProjecttoList(mProject);
                }

                Intent i = new Intent(getActivity(),TaskListActivity.class);
                i.putExtra("Current_Project", mProject);
                getActivity().startActivity(i);

                /*Fragment task = SingleTaskFragmentActivity.newInstance(null);
                FragmentTransaction ft;
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.project_fragment_container, task);
                ft.addToBackStack(null);
                ft.commit();*/
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
                date.setHours(mProject.getHour());
                date.setMinutes(mProject.getMinute());
                mProject.setDeadlineDate(date);
                mProject.setDeadlineTime(date);
                updateDate();
                updateTime();
            }

            if (requestCode == REQUEST_TIME) {
                Date time = (Date) data
                        .getSerializableExtra(TimeFragment.USER_TIME);
                time.setYear(mProject.getYear());
                time.setMonth(mProject.getMonth());
                time.setDate(mProject.getDay());
                mProject.setDeadlineTime(time);
                mProject.setDeadlineDate(time);
                updateTime();
                updateDate();
            }
    }

    private void updateDate() {
        mDateButton.setText(mProject.getDeadlineDate().toString());
    }

    private void updateTime() {
        mTimeButton.setText(mProject.getDeadlineTime().toString());
    }

}
