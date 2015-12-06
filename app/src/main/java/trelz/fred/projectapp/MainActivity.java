package trelz.fred.projectapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    //private FloatingActionButton mFloatinActionButton;

    private static final String EXTRA_TASK_ID =
            "trelz.fred.projectapp.task_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_TASK_ID, crimeId);
        return intent;
    }

    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mFloatinActionButton = (FloatingActionButton) findViewById(R.id.new_project);
        mFloatinActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ProjectFragment newFragment = (ProjectFragment) fm.findFragmentByTag("tag");

                if (newFragment == null)
                {
                    newFragment = new ProjectFragment();
                    ft.add(newFragment, "tag");
                    //ft.commit();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
                else
                {
                    ft.remove(newFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }
            }
        });*/
    //}

    /*private void initProjectNameStrings() {
        for (int i = 0; i < projectArrayList.size(); i++) {
            
        }
    }*/

    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_TASK_ID);
        return ProjectFragment.newInstance(taskId);
    }

    //ArrayList<Project> projectArrayList;

}
