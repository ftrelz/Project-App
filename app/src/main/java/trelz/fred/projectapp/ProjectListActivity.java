package trelz.fred.projectapp;

import android.support.v4.app.Fragment;

/**
 * Created by jc_cisneros21 on 11/30/15.
 */
public class ProjectListActivity extends SingleProjectFragmentActivity {

    protected Fragment createFragment()
    {
        return new ProjectListFragment();
    }



}
