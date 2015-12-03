package trelz.fred.projectapp;

import android.support.v4.app.Fragment;

/**
 * Created by jc_cisneros21 on 11/30/15.
 */
public class TaskListActivity extends SingleFragmentActivity {

    protected Fragment createFragment()
    {
        return new TaskListFragment();
    }
}
