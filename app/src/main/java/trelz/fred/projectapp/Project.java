package trelz.fred.projectapp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Fred on 11/20/2015.
 */
public class Project {

    public Project(String name, Calendar deadline) {
        this.name = name;
        this.deadline = deadline;
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

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void addTask(int index, Task item) {
        taskArrayList.add(index, item);
    }

    public void deleteTask(int index) {
        taskArrayList.remove(index);
    }

    String name;
    Calendar deadline;
    String description;
    ArrayList<Task> taskArrayList;

}
