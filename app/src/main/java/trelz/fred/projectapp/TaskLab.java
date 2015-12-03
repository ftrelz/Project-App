package trelz.fred.projectapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jc_cisneros21 on 11/24/15.
 */
public class TaskLab {

    private static TaskLab sTaskLab;
    private ArrayList<Task> mTasks;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }

    private TaskLab(Context context) {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setName("Task #" + i);
            mTasks.add(task);
        }
    }

    public List<Task> getTasks()
    {
        return  mTasks;
    }

    public Task getTask(UUID id) {
        for (Task task : mTasks) {
            System.out.println(task.getId());
            System.out.println(id);
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

}
