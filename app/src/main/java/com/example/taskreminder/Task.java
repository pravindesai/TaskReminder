package com.example.taskreminder;

import java.io.Serializable;
import java.sql.Timestamp;

public class Task implements Serializable {

    int id;
    int ImageId;
    String TaskName;
    String TaskDescription;

    public Task(int id, int imageId, String taskName, String taskDescription) {
        this.id = id;
        ImageId = imageId;
        TaskName = taskName;
        TaskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }
}
