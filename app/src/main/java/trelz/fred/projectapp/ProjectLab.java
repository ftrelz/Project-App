package trelz.fred.projectapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jc_cisneros21 on 11/24/15.
 */
public class ProjectLab {

    private static ProjectLab sProjectLab;
    private ArrayList<Project> mProjects;

    public static ProjectLab get(Context context) {
        if (sProjectLab == null) {
            sProjectLab = new ProjectLab(context);
        }
        return sProjectLab;
    }

    private ProjectLab(Context context) {
        mProjects = new ArrayList<>();

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

    public Project getProject(int id) {

        if (id < mProjects.size()) {
            return mProjects.get(id);
        }
        return null;
    }

    public void addProjecttoList(Project project)
    {
        mProjects.add(project);
        System.out.println(mProjects.get(0).getName());
    }

    public int getProjectListSize()
    {
        return mProjects.size();
    }

}
