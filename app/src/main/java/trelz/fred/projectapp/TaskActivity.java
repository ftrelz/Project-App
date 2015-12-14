package trelz.fred.projectapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class TaskActivity extends SingleTaskFragmentActivity {

    private static final String EXTRA_TASK_ID =
            "trelz.fred.projectapp.task_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, crimeId);
        return intent;
    }

    protected Fragment createFragment() {
        UUID projectId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(projectId);
    }
}