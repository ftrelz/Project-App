package trelz.fred.projectapp;

import android.support.v4.app.Fragment;

/**
 * Created by jc_cisneros21 on 11/30/15.
 * This class is the first activity, or main activity in a way,
 * that is start up on launch. In the manifest, this shows it.
 */
public class ProjectListActivity extends SingleProjectFragmentActivity {

    // createFragment is used by SingleProjectFragmentActivity
    protected Fragment createFragment()
    {
        return new ProjectListFragment();
    }

}
