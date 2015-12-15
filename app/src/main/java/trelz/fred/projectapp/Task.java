package trelz.fred.projectapp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ftrel on 11/20/2015.
 */
public class Task {

    public Task(String name, Date deadline, Date time) {
        mName = name;
        mDeadlineDate = deadline;
        mDeadlineTime = time;
    }

    public Task() {
        mId = UUID.randomUUID();
        mName = "New Task";
        mDeadlineDate = new Date();
        mDeadlineTime = new Date();
        next = false;
        complete = false;
    }

    public String getName() {
        return mName;
    }

    public UUID getUUID() {
        return mId;
    }

    public void setName(String newName) {
        mName = newName;
    }

    public Date getDeadlineDate() {
        return mDeadlineDate;
    }

    public void setDeadlineDate(Date newDeadlineDate) {
        mDeadlineDate = newDeadlineDate;
    }

    public Date getDeadlineTime() {
        return mDeadlineTime;
    }

    public void setDeadlineTime(Date newDeadlineTime) {
        mDeadlineTime = newDeadlineTime;
    }

    public void addSubTask(int index, SubTask task) {
        subTaskArrayList.add(index, task);
    }

    public void deleteSubTask(int index) {
        subTaskArrayList.remove(index);
    }

    public int getHour() {
        return mDeadlineTime.getHours();
    }

    public int getMinute() {
        return mDeadlineTime.getMinutes();
    }

    public int getYear() {
        return mDeadlineDate.getYear();
    }

    public int getMonth() {
        return mDeadlineDate.getMonth();
    }

    public int getDay() {
        return mDeadlineDate.getDay();
    }

    public void setBool(boolean isTrue) {
        next = isTrue;
    }

    public boolean getBool() {return next;}

    public void setCompleted(boolean bool) {
        complete = bool;
    }

    public boolean getCompleted(boolean bool) {
        return complete;
    }

    private UUID mId;
    private boolean complete;
    private boolean next;
    private String mName;
    private Date mDeadlineDate;
    private Date mDeadlineTime;
    private ArrayList<SubTask> subTaskArrayList;
}
