package br.edu.ifsp.app13_listatarefas.presenter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.app13_listatarefas.model.DAO.ITaskDAO;
import br.edu.ifsp.app13_listatarefas.model.DAO.TaskDAOSingleton;
import br.edu.ifsp.app13_listatarefas.model.entities.Task;
import br.edu.ifsp.app13_listatarefas.mvp.MainMVP;
import br.edu.ifsp.app13_listatarefas.utils.Constant;
import br.edu.ifsp.app13_listatarefas.view.TasksDetailsActivity;
import br.edu.ifsp.app13_listatarefas.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter{
    private MainMVP.View view;
    private Context context;
    private ITaskDAO dao;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TaskDAOSingleton.getInstance();
        context = view.getContext();
        dao.setContext(context);
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), TasksDetailsActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails(Task task) {
        Intent intent = new Intent(view.getContext(), TasksDetailsActivity.class);
        intent.putExtra(Constant.ATTR_TITLE, task.getTitle());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void favoriteTask(Task task) {
        task.setImportant(!task.isImportant());
        dao.update(task.getTitle(), task);
    }

    public void deleteTask(Task task){
        dao.delete(task);
    }
}