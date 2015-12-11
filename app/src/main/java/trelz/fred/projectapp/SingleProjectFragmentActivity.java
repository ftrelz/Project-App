package trelz.fred.projectapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/**
 * Created by jc_cisneros21 on 12/1/15.
 */
public abstract class SingleProjectFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();
    private Button mProject;

    // Starts the Fragment
    protected void onCreate(Bundle savedInstanceState) {

        // create an FragmentActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.project_fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.project_fragment_container, fragment).commit();
        }

        mProject = (Button) findViewById(R.id.add_project);
        mProject.setVisibility(View.INVISIBLE);
        mProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View b = findViewById(R.id.add_project);
                //b.setVisibility(View.GONE);
                Project p = new Project();
                Fragment project = ProjectFragment.newInstance(p.getUUID());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.project_fragment_container, project);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

    }
}
