package trelz.fred.projectapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ftrel on 11/20/2015.
 */
public class Task
{

    public Task(String name, Date deadline)
    {
        mName = name;
        mDeadline= deadline;
    }

    public Task()
    {
        mId = UUID.randomUUID();
        mDeadline = new Date();
    }

    public String getName() { return mName; }

    public UUID getId() { return mId; }

    public void setName(String newName) {
        mName = newName;
    }

    public Date getDeadline() {
        return mDeadline;
    }

    public void setDeadline(Date newDeadline) {
        mDeadline = newDeadline;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public String getDescription() {
        return description;
    }

    public void addSubTask(int index, SubTask task) {
        subTaskArrayList.add(index, task);
    }

    public void deleteSubTask(int index) {
        subTaskArrayList.remove(index);
    }

    private UUID mId;
    private String mName;
    private Date mDeadline;
    private String description;
    private ArrayList<SubTask> subTaskArrayList;
}
