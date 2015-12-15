package trelz.fred.projectapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import trelz.fred.projectapp.TaskFragment;

/**
 * Created by jc_cisneros21 on 12/1/15.
 */
public abstract class SingleTaskFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();
    private Button mTask;

    // Starts the Fragment
    protected void onCreate(Bundle savedInstanceState) {

        // create an FragmentActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.task_fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.task_fragment_container, fragment).commit();
        }

        mTask = (Button) findViewById(R.id.add_task);
        mTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task t = new Task();
                Fragment task = TaskFragment.newInstance(t.getUUID());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.task_fragment_container, task);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }
}