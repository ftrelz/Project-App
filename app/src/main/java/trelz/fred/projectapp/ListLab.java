package trelz.fred.projectapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jc_cisneros21 on 11/24/15.
 */
public class ListLab {

    private static ListLab sListLab;
    private ArrayList<Object> mObjects;

    public static ListLab get(Context context) {
        if (sListLab == null) {
            sListLab = new ListLab(context);
        }
        return sListLab;
    }

    private ListLab(Context context) {
        mObjects = new ArrayList<>();

        /*for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setName("Task #" + i);
            mObjects.add(task);
        }*/
    }

    public List<Object> getObjects()
    {
        return mObjects;
    }

    public Object getObject(int id) {

        if (id < mObjects.size()) {
            return mObjects.get(id);
        }
        return null;
    }

    public void addObjecttoList(Object o)
    {
        mObjects.add(o);
    }

    public int getListSize()
    {
        return mObjects.size();
    }
}
