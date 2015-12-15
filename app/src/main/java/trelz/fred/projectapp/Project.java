package trelz.fred.projectapp;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Fred on 11/20/2015.
 * Projects will contain its certain tasks and if
 * accessed, these tasks will then show up it the
 * tasks list layout
 */
public class Project implements Serializable {

    public Project(String name, Date deadlineDate, Date deadlineTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.mDeadlineDate = deadlineDate;
        this.mDeadlineTime = deadlineTime;
    }

    public Project() {
        this.id = UUID.randomUUID();
        this.name = "Project Title";
        this.description = "Project Description";
        this.mDeadlineDate = new Date();
        this.mDeadlineTime = new Date();
        this.next = false;
        this.taskArrayList = new ArrayList<Task>();
    }

    public UUID getUUID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDeadlineDate(Date deadline) {
        this.mDeadlineDate = deadline;
    }

    public Date getDeadlineDate() {
        return mDeadlineDate;
    }

    public void setDeadlineTime(Date deadline) {
        this.mDeadlineTime = deadline;
    }

    public Date getDeadlineTime() {
        return mDeadlineTime;
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

    public ArrayList<Task> getTasks() {
        return taskArrayList;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    private boolean next;
    private UUID id;
    private String name;
    private Date mDeadlineDate;
    private Date mDeadlineTime;
    private String description;
    private ArrayList<Task> taskArrayList;

}
