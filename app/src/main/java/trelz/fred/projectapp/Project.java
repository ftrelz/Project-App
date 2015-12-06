package trelz.fred.projectapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Fred on 11/20/2015.
 */
public class Project {

    public Project(String name, Date deadlineDate, Date deadlineTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.mDeadlineDate = deadlineDate;
        this.mDeadlineTime = deadlineTime;
    }

    public Project() {
        this.id = UUID.randomUUID();
        this.mDeadlineDate = new Date();
        this.mDeadlineTime = new Date();
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

    private UUID id;
    private String name;
    private Date mDeadlineDate;
    private Date mDeadlineTime;
    private String description;
    ArrayList<Task> taskArrayList;

}
