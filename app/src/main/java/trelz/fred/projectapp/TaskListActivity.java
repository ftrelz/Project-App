package trelz.fred.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by jc_cisneros21 on 11/30/15.
 */
public class TaskListActivity extends SingleTaskFragmentActivity {

    private Project currentProject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getIntent().getExtras();

        Intent i = getIntent();

        currentProject = (Project) i.getSerializableExtra("Current_Project");
        System.out.println(currentProject.getName());
    }

    public Project getProject()
    {
        return currentProject;
    }

    protected Fragment createFragment()
    {
        return new TaskListFragment();
    }
}
