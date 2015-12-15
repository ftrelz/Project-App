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
public class ProjectFragment extends Fragment {

    private static final String ARG_PROJECT_ID = "project_id";
    public static Project mProject;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mTimeButton;
    private Button mNextButton;
    private CheckBox mDeleteBox;
    private boolean delete_on;
    private int project_index;
    private boolean new_project_check;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    // method to add Projects to ProjectLab
    private void addProjecttoList(Project object)
    {
        ProjectLab.get(getActivity()).addProjecttoList(object);
    }

    // Getting a certain project
    public Project getProject() {
        return mProject;
    }

    // Method for Instance of ProjectFragment
    public static ProjectFragment newInstance(UUID projectId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectId);

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Starts ProjectFragment and is used to to retrieve the
    // Project the user is working on.
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Project p = new Project();
        UUID projectId = (UUID) getArguments().getSerializable(ARG_PROJECT_ID);
        View b = this.getActivity().findViewById(R.id.add_project);
        b.setVisibility(View.INVISIBLE);

        // Retrieves the project we are working on or if a new project
        // we will create a new Instance of Project.
        if (ProjectLab.get(getActivity()).getProjectListSize() == 0) {
            mProject = p;
        } else {
            for (int i = 0; i < ProjectLab.get(getActivity()).getProjectListSize(); i++) {
                p = ProjectLab.get(getActivity()).getProject(i);
                if (projectId.compareTo(p.getUUID()) != 0) {
                    p = new Project();
                    mProject = p;
                }
                else
                {
                    project_index = i;
                    mProject = p;
                    return;
                }
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);

        delete_on = false;

        // Retrieves the project's title the user inputs
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

        // Retrieves the Project's Description from the user
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

        // Retrieves the date from the user
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

        // Retrieves the time from the user
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

        // If the box is check, the project is deleted
        mDeleteBox = (CheckBox) v.findViewById(R.id.delete_project);
        mDeleteBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Project p = new Project();
                // If we check the box we delete the project
               if (mDeleteBox.isChecked() == true) {
                   delete_on = true;
                   if (mProject.getBool() == false) {
                       new_project_check = true;
                       mProject.setBool(true);
                   } else {
                       ProjectLab.get(getActivity()).deleteProject(project_index);
                   }
               }
               else {
                   // If we uncheck the box, we keep the project
                   delete_on = false;
                   if (new_project_check == true) {
                       new_project_check = false;
                       mProject.setBool(false);
                   }
                   else {
                       ProjectLab.get(getActivity()).addProjectIndex(project_index,mProject);
                   }
               }
            }
        });

        // If the certain conditions are met, the project is deleted
        if (mProject.getBool() == false && delete_on == false) {
            mProject.setBool(true);
            addProjecttoList(mProject);
        }

        // The next button opens up the TaskListActivity, which is the
        // task list
        mNextButton = (Button) v.findViewById(R.id.poject_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( delete_on == false) {
                    Intent i = new Intent(getActivity(), TaskListActivity.class);
                    //i.putExtra("Current_Project", mProject);
                    getActivity().startActivity(i);
                }
            }
        });

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
            // retrieves the Date from DateFragment
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

            // retrieves the Time from TimeFragment
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

    // Updates the Date
    private void updateDate() {
        mDateButton.setText(mProject.getDeadlineDate().toString());
    }

    // Udaptes the Time
    private void updateTime() {
        mTimeButton.setText(mProject.getDeadlineTime().toString());
    }

}
