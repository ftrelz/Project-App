package trelz.fred.projectapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jc_cisneros21 on 12/1/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();
    private Button mProject;

    // Starts the Fragment
    protected void onCreate(Bundle savedInstanceState) {
        // create an FragmentActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        mProject = (Button) findViewById(R.id.add_project);
        mProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("This is printing!");

                Fragment project = ProjectFragment.newInstance(null);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, project);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

    }
}
