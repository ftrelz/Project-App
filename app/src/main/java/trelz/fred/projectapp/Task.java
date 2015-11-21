package trelz.fred.projectapp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ftrel on 11/20/2015.
 */
public class Task {
    public Task(String name, Calendar deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar newDeadline) {
        deadline = newDeadline;
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

    String name;
    Calendar deadline;
    String description;
    ArrayList<SubTask> subTaskArrayList;
}
