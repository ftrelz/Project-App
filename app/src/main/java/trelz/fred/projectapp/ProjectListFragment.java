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
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProjectListFragment extends Fragment {

    private RecyclerView mProjectRecyclerView;
    private ProjectAdapter mAdapter;
    private Button mProject;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        ProjectLab projectLab = ProjectLab.get(getActivity());
        List<Project> projects = projectLab.getProjects();

        mProjectRecyclerView = (RecyclerView) view
                    .findViewById(R.id.project_recycler_view);
        mProjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mAdapter == null)
        {
            mAdapter = new ProjectAdapter(projects);
        }

        mProjectRecyclerView.setAdapter(mAdapter);


        mProject = (Button) this.getActivity().findViewById(R.id.add_project);
        mProject.setVisibility(View.VISIBLE);

        return view;
    }

    @Override

    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ProjectLab projectLab = ProjectLab.get(getActivity());
        List<Project> projects = projectLab.getProjects();

        mAdapter.notifyDataSetChanged();
        mProjectRecyclerView.setAdapter(mAdapter);
    }

    private class ProjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mTimeTextView;

        private Project mProject;

        public ProjectHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_project_title);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_project_description);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_project_time);
        }

        public void bindProject(Project p) {
            mProject = p;
            mTitleTextView.setText(mProject.getName());
            mTimeTextView.setText(mProject.getDeadlineTime().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ProjectActivity.newIntent(getActivity(), mProject.getUUID());
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
            View view = layoutInflater.inflate(R.layout.list_project, parent, false);
            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, int position) {
            System.out.println("The position is " + position);
            Project project = mProjects.get(position);
            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }
    }
}
