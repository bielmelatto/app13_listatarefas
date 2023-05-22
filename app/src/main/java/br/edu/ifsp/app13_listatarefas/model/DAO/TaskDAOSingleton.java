package br.edu.ifsp.app13_listatarefas.model.DAO;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifsp.app13_listatarefas.model.entities.Task;

public class TaskDAOSingleton implements ITaskDAO{
    private static TaskDAOSingleton instance = null;
    private Context context;
    private List<Task> dataset;

    private TaskDAOSingleton() {
        dataset = new ArrayList<>();
    }

    public static TaskDAOSingleton getInstance(){
        if(instance == null)
            instance = new TaskDAOSingleton();
        return instance;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public void create(Task task) {
        if(task != null){
            dataset.add(task);
            Collections.sort(dataset);
        }
    }

    @Override
    public boolean update(String oldTitle, Task task) {
        Task inDataset;
        inDataset = dataset.stream()
                .filter(task1 -> task1.getTitle().equals(oldTitle))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setTitle(task.getTitle());
            inDataset.setDescription(task.getDescription());
            inDataset.setImportant(task.isImportant());
            Collections.sort(dataset);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Task task) {
        dataset.remove(task);
    }

    @Override
    public Task findByTitle(String title) {
        return dataset.stream()
                .filter(task -> task.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return dataset;
    }
}
