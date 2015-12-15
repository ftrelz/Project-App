package trelz.fred.projectapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ProjectActivity extends SingleProjectFragmentActivity {

    private static final String EXTRA_PROJECT_ID =
            "trelz.fred.projectapp.project_id";

    // Creates newIntent of ProjectActivity
    public static Intent newIntent(Context packageContext, UUID projectId) {
        Intent intent = new Intent(packageContext, ProjectActivity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectId);
        return intent;
    }

    // create Fragment is used by SingleFragmentActivity
    protected Fragment createFragment() {
        UUID projectId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_PROJECT_ID);
        return ProjectFragment.newInstance(projectId);
    }
}
