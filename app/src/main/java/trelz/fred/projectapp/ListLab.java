package trelz.fred.projectapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jc_cisneros21 on 11/24/15.
 */
public class ListLab {

    private static ListLab sListLab;
    private ArrayList<Project> mProjects;
    private ArrayList<Task> mTasks;

    public static ListLab get(Context context) {
        if (sListLab == null) {
            sListLab = new ListLab(context);
        }
        return sListLab;
    }

    private ListLab(Context context) {
        mProjects = new ArrayList<>();
        mTasks = new ArrayList<>();

        /*for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setName("Task #" + i);
            mObjects.add(task);
        }*/
    }

    public List<Project> getProjects()
    {
        return mProjects;
    }

    public List<Task> getTasks()
    {
        return mTasks;
    }

    public Project getProject(int id) {

        if (id < mProjects.size()) {
            return mProjects.get(id);
        }
        return null;
    }

    public Task getTask(int id) {

        if (id < mTasks.size()) {
            return mTasks.get(id);
        }
        return null;
    }

    public void addObjecttoList(Object o)
    {
        Task test = new Task();
        if ( o.getClass() == test.getClass())
        {
            mTasks.add((Task) o);
        }
        else
        {
            mProjects.add((Project) o);
            System.out.println(mProjects.get(0).getName());
        }
    }

    public int getProjectListSize()
    {
        return mProjects.size();
    }

    public int getTaskListSize()
    {
        return mTasks.size();
    }

}
