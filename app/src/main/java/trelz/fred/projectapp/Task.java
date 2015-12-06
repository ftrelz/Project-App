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
        mDeadlineDate = new Date();
        mDeadlineTime = new Date();
    }

    public String getName() {
        return mName;
    }

    public UUID getId() {
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

    private UUID mId;
    private String mName;
    private Date mDeadlineDate;
    private Date mDeadlineTime;
    private String description;
    private ArrayList<SubTask> subTaskArrayList;
}
