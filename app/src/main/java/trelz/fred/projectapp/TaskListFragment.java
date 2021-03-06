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
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;
    private Button mTask;
    private Project mProject;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mProject = ((TaskListActivity) getActivity()).getProject();

        TaskLab taskLab = TaskLab.get(getActivity());
        taskLab.setProject(mProject);

        List<Task> tasks = taskLab.getTasks();

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mAdapter == null)
        {
            mAdapter = new TaskAdapter(tasks);
        }

        mTaskRecyclerView.setAdapter(mAdapter);


        mTask = (Button) this.getActivity().findViewById(R.id.add_task);
        mTask.setVisibility(View.VISIBLE);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

        TaskLab taskLab = TaskLab.get(getActivity());
        List<Task> task = taskLab.getTasks();

        mAdapter.notifyDataSetChanged();
        mTaskRecyclerView.setAdapter(mAdapter);

    }

    private class TaskHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mTimeTextView;

        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_task_title);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_task_time);
        }

        public void bindTask(Task p) {
            mTask = p;
            mTitleTextView.setText(mTask.getName());
            mTimeTextView.setText(mTask.getDeadlineTime().toString());
        }

        @Override
        public void onClick(View v) {

            Intent intent = TaskActivity.newIntent(getActivity(), mTask.getUUID());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks =  tasks;
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
