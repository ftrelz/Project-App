package trelz.fred.projectapp;

/**
 * Created by jc_cisneros21 on 11/30/15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskListFragment extends Fragment {

    private RecyclerView mTaskRecyclerView;
    private ProjectAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override

    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ListLab listLab = ListLab.get(getActivity());
        List<Project> projects;
        ArrayList<Project> temp = new ArrayList<>();
        for (int i = 0; i < ListLab.get(getActivity()).getListSize(); i++) {
            temp.add((Project)ListLab.get(getActivity()).getObject(i));
        }
        projects = temp;

        if (mAdapter == null) {
            mAdapter = new ProjectAdapter(projects);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ProjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mTimeTextView;

        private Project mProject;

        public ProjectHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_task_title);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_task_description);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_task_time);
        }

        public void bindTask(Project p) {
            mProject = p;
            mTitleTextView.setText(mProject.getName());
            mTimeTextView.setText(mProject.getDeadlineTime().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MainActivity.newIntent(getActivity(), mProject.getUUID());
            startActivity(intent);
        }
    }

    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {

        private List<Project> mProjects;

        public ProjectAdapter(List<Project> projects) {
            mProjects =  projects;
        }

        @Override
        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_task, parent, false);
            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, int position) {
            Project project = mProjects.get(position);
            holder.bindTask(project);
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }
    }
}
