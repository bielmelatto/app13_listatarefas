package br.edu.ifsp.app13_listatarefas.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.app13_listatarefas.R;
import br.edu.ifsp.app13_listatarefas.mvp.MainMVP;
import br.edu.ifsp.app13_listatarefas.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private MainMVP.Presenter presenter;
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        presenter.populateList(recyclerView);

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        presenter = new MainPresenter(this);
        presenter.populateList(recyclerView);
        super.onResume();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findViews(){
        actionButton = findViewById(R.id.fab_add_task);
        recyclerView = findViewById(R.id.recyclerview_task);
    }

    private void setListener(){
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openDetails();
            }
        });


    }
}