package br.edu.ifsp.app13_listatarefas.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.app13_listatarefas.model.entities.Task;

public interface MainMVP {
    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails();

        void openDetails(Task task);

        void populateList(RecyclerView recyclerView);

        void favoriteTask(Task task);

        void deleteTask(Task task);
    }
}