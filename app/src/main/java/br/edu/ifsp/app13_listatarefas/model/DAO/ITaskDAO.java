package br.edu.ifsp.app13_listatarefas.model.DAO;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.app13_listatarefas.model.entities.Task;

public interface ITaskDAO {
    void create(Task task);

    boolean update(String oldTitle, Task task);

    void delete(Task task);

    void setContext(Context context);

    Task findByTitle(String title);

    List<Task> findAll();

}