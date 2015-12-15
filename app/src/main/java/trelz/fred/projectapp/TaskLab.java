package trelz.fred.projectapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jc_cisneros21 on 11/24/15.
 */
public class TaskLab {

    private static TaskLab sTaskLab;
    private ArrayList<Task> mTasks;
    private Project mProject;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }

    private TaskLab(Context context) {
        mTasks = new ArrayList<>();
    }

    public void setProject(Project p)
    {
        mProject = p;
        mTasks = mProject.getTasks();
    }

    public List<Task> getTasks()
    {
        return mTasks;
    }

    public Task getTask(int id) {

        if (id < mTasks.size()) {
            return mTasks.get(id);
        }
        return null;
    }

    public void addTasktoList(Task task)
    {

        mTasks.add(task);
        mProject.setTasks(mTasks);
    }

    public void deleteTask(int index)
    {
        mTasks.remove(index);
        mProject.setTasks(mTasks);
    }

    public void addTaskIndex(int index, Task task) {
        mTasks.add(index, task);
        mProject.setTasks(mTasks);
    }

    public int getTaskListSize()
    {
        return mTasks.size();
    }

}
