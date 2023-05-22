package br.edu.ifsp.app13_listatarefas.model.entities;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Task implements Comparable<Task>{
    private String title;
    private String description;
    private String date;
    private boolean important;

    public Task(String title, String description){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.title = title;
        this.description = description;
        this.date = localDate.format(formatter);
    }

    public Task(String title, String description, boolean important) {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.title = title;
        this.description = description;
        this.date = localDate.format(formatter);
        this.important = important;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Boolean isImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + title;
    }

    @Override
    public int compareTo(Task task) {
        return Comparator.comparing(Task::isImportant).reversed().compare(this, task);
    }

}
