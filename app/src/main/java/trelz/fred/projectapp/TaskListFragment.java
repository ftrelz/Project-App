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

import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;

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
        TaskLab crimeLab = TaskLab.get(getActivity());
        List<Task> crimes = crimeLab.getTasks();

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(crimes);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;

        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_task_title);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_task_description);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_task_date);
        }

        public void bindTask(Task task) {
            mTask = task;
            mTitleTextView.setText(mTask.getName());
            mDateTextView.setText(mTask.getDeadlineDate().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MainActivity.newIntent(getActivity(), mTask.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;

        public TaskAdapter(List<Task> crimes) {
            mTasks = crimes;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_task, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}
